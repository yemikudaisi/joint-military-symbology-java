package com.github.yemikudaisi.jmsj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.yemikudaisi.jmsj.symbology.Entity;
import com.github.yemikudaisi.jmsj.symbology.EntityModifierHeirarchy;
import com.github.yemikudaisi.jmsj.symbology.EntitySubType;
import com.github.yemikudaisi.jmsj.symbology.EntityType;
import com.github.yemikudaisi.jmsj.symbology.MilitarySymbol;
import com.github.yemikudaisi.jmsj.symbology.Modifier;
import com.github.yemikudaisi.jmsj.symbology.ModifierTypes;
import com.github.yemikudaisi.jmsj.symbology.SymbolSets;

public class MilitarySymbolFactory
{
	static Logger logger = Logger.getLogger(SvgFactory.class.getName());
	public static EntityModifierHeirarchy getEnityModifierHeirarchyForSymbolSet(SymbolSets symbolSet) {
		ResourceManager resourceManager = new ResourceManager();
		
    	String entitiesfilePath = resourceManager.getEnitiesCsvResourcePath(symbolSet);
    	String areaEntitiesfilePath = resourceManager.getAreaEnitiesCsvResourcePath(symbolSet);
    	String lineEntitiesfilePath = resourceManager.getLineEnitiesCsvResourcePath(symbolSet);
    	String pointEntitiesfilePath = resourceManager.getPointEnitiesCsvResourcePath(symbolSet);

    	EntityModifierHeirarchy h = new EntityModifierHeirarchy(symbolSet);
    	
    	addEntitiesTree(entitiesfilePath, h.getEntities());
    	addEntitiesTree(areaEntitiesfilePath, h.getEntities());
    	addEntitiesTree(lineEntitiesfilePath, h.getEntities());
    	addEntitiesTree(pointEntitiesfilePath, h.getEntities());
    	
    	addSectorModifiers(ModifierTypes.One, symbolSet,h.getSectorModifierOnes());
    	addSectorModifiers(ModifierTypes.Two, symbolSet,h.getSectorModifierTwos());
    	return h;
	}
	
    public static void addEntitiesTree(String filePath, List<Entity> enitiesList) {   	

    	Entity lastEntity = null;
    	EntityType lastEntityType = null;
    	
    	//TODO: Consider symbols with special entity category(Line, Area and Point)
    	try {
    		File f = ResourceManager.getResourceFile(filePath);
        	BufferedReader csvReader = new BufferedReader(new FileReader(f));
        	String row = csvReader.readLine(); // Skip the CSV header
        	while ((row = csvReader.readLine()) != null) {
        	    String[] data = row.split(",");
        	    String name = data[0];
        	    String code = data[1];
        	    String[] split = name.split(":");
        	    switch(split.length) {
        	    case 1:
        	    	lastEntity = new Entity(split[0].trim(), code);
        	    	enitiesList.add(lastEntity);
        	    	break;
        	    case 2:
        	    	lastEntityType = new EntityType(split[1].trim(), code);
        	    	if(lastEntity != null) {
        	    		lastEntity.getTypes().add(lastEntityType);
        	    	}
        	    	break;
        	    case 3:
        	    	EntitySubType s = new EntitySubType(split[2].trim(), code);
        	    	if(lastEntityType!=null) {
        	    		lastEntityType.getSubTypes().add(s);
        	    	}
        	    	break;
        	    }
        	    // do something with the data
        	}
        	csvReader.close();
        	
        	
    	}catch(Exception e) {
    		String name = Paths.get(filePath).getFileName().toString();
    		name = name.substring(0,name.length()-4);
    		name = name.replace('_', ' ');
    		name = name.substring(13,name.length());
    				
    		logger.log(Level.INFO, "Unable to build enities for "+name);
    		return;
    	}
    }
    
    private static void addSectorModifiers(ModifierTypes modifierType, SymbolSets set,List<Modifier> modifiersList) {
    	
    	ResourceManager resourceManager = new ResourceManager();
    	try {
    		String path = resourceManager.getModifierCsvResourcePath(set, modifierType);
    		File f = ResourceManager.getResourceFile(path);
        	BufferedReader csvReader = new BufferedReader(new FileReader(f));
        	String row = csvReader.readLine(); // Skip the CSV header
        	while ((row = csvReader.readLine()) != null) {
        	    String[] data = row.split(",");
        	    String name = data[0].trim();
        	    String code = data[1].trim();
        	    modifiersList.add(new Modifier(name, code));
        	}
        	csvReader.close();
    	}catch(Exception e) {
    		logger.log(Level.INFO, "Unable to build sector modifiers("+modifierType.toString()+") for "+set);
    		return;
    	} 
    }
}
