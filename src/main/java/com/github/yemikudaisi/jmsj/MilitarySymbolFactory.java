package com.github.yemikudaisi.jmsj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import com.github.yemikudaisi.jmsj.symbology.Entity;
import com.github.yemikudaisi.jmsj.symbology.EntityModifierHeirarchy;
import com.github.yemikudaisi.jmsj.symbology.EntitySubType;
import com.github.yemikudaisi.jmsj.symbology.EntityType;
import com.github.yemikudaisi.jmsj.symbology.MilitarySymbol;
import com.github.yemikudaisi.jmsj.symbology.SymbolSets;

public class MilitarySymbolFactory
{
    
    public static EntityModifierHeirarchy build(SymbolSets symbolSet) {
    	ResourceManager fs = new ResourceManager();
    	String filePath = fs.getEnitiesFileName(symbolSet);
    	EntityModifierHeirarchy h = new EntityModifierHeirarchy(symbolSet);
    	Entity lastEntity = null;
    	EntityType lastEntityType = null;
    	
    	// TODO: Add support for modifiers
    	//TODO: Consider symbols with Line, Area and Point
    	try {
    		File f = ResourceManager.getFile(filePath);
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
        	    	h.getEntities().add(lastEntity);
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
    	}catch(IOException e) {
    		
    	}
    	return h;
    }
}
