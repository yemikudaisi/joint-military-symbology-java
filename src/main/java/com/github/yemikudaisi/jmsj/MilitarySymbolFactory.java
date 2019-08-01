package com.github.yemikudaisi.jmsj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.yemikudaisi.jmsj.symbology.Amplifier;
import com.github.yemikudaisi.jmsj.symbology.EchelonAmplifiers;
import com.github.yemikudaisi.jmsj.symbology.Entity;
import com.github.yemikudaisi.jmsj.symbology.EntitySubType;
import com.github.yemikudaisi.jmsj.symbology.EntityType;
import com.github.yemikudaisi.jmsj.symbology.EquipmentMobilityAmplifiers;
import com.github.yemikudaisi.jmsj.symbology.HQTFDummy;
import com.github.yemikudaisi.jmsj.symbology.MilitarySymbol;
import com.github.yemikudaisi.jmsj.symbology.Modifier;
import com.github.yemikudaisi.jmsj.symbology.ModifierTypes;
import com.github.yemikudaisi.jmsj.symbology.NavalTowedArrayAmplifiers;
import com.github.yemikudaisi.jmsj.symbology.NotApplicableAmplifier;
import com.github.yemikudaisi.jmsj.symbology.StandardEntityOnes;
import com.github.yemikudaisi.jmsj.symbology.StandardEntityTwos;
import com.github.yemikudaisi.jmsj.symbology.Status;
import com.github.yemikudaisi.jmsj.symbology.StatusAmplifierModes;
import com.github.yemikudaisi.jmsj.symbology.SymbolSetEntityModifierTree;
import com.github.yemikudaisi.jmsj.symbology.SymbolSets;

public class MilitarySymbolFactory
{
	static Logger logger = Logger.getLogger(SvgFactory.class.getName());
	static StatusAmplifierModes amplifierMode = StatusAmplifierModes.Default;
	
	public static MilitarySymbol createSymbol(String code) {
		MilitarySymbol milSym = new MilitarySymbol();
		milSym.setStatusAmplifierMode(amplifierMode);
		String sidc = code.replaceAll(" ", "").replaceAll("-", "");
		
		milSym.setStandardEntityOne(StandardEntityOnes.getEnum(sidc.substring(2,3)));
    	milSym.setStandardEntityTwo(StandardEntityTwos.getEnum(sidc.substring(3,4)));
    	milSym.setSymbolSet(SymbolSets.getEnum(sidc.substring(4,6)));
    	
    	milSym.setStatusAmplifier(Status.getEnum(sidc.substring(6,7)));
    	milSym.setHqTFDummy(HQTFDummy.getEnum(sidc.substring(7,8)));
    	milSym.setAmplifier(getAmplifier(sidc.substring(8,10)));
    	
    	milSym.setEntity(new Entity(sidc.substring(10,12),sidc.substring(10,12)+"0000"));
    	milSym.setEntityType(new EntityType(sidc.substring(12,14),sidc.substring(10,14)+"00"));
    	milSym.setEntitySubType(new EntitySubType(sidc.substring(14,16),sidc.substring(10,16)));
    	
    	milSym.setSectorOneModifier(new Modifier(sidc.substring(16,18),sidc.substring(16,18)));
    	milSym.setSectorTwoModifier(new Modifier(sidc.substring(18,20),sidc.substring(18,20)));
    	return milSym;
	}
	
	public static void setStatusAmplifierMode(StatusAmplifierModes mode) {
		amplifierMode = mode;
	}
	
	private static Amplifier getAmplifier(String amplifierSidc) {
		char[] c = amplifierSidc.toCharArray();
				switch(c[0]) {
				case '1':
				case '2':
					return EchelonAmplifiers.getEnum(amplifierSidc);
				case '3':
				case '4':
				case '5':
					return EquipmentMobilityAmplifiers.getEnum(amplifierSidc);
				case '6':
					return NavalTowedArrayAmplifiers.getEnum(amplifierSidc);
				default :
					return NotApplicableAmplifier.Unspecified;
				}
	}
	
	public static SymbolSetEntityModifierTree createSymbolSetEntityModifierTree(SymbolSets symbolSet) {
		ResourceManager resourceManager = new ResourceManager();
		
    	String entitiesfilePath = resourceManager.getEnitiesCsvResourcePath(symbolSet);
    	String areaEntitiesfilePath = resourceManager.getAreaEnitiesCsvResourcePath(symbolSet);
    	String lineEntitiesfilePath = resourceManager.getLineEnitiesCsvResourcePath(symbolSet);
    	String pointEntitiesfilePath = resourceManager.getPointEnitiesCsvResourcePath(symbolSet);

    	SymbolSetEntityModifierTree h = new SymbolSetEntityModifierTree(symbolSet);
    	
    	addEntitiesTree(entitiesfilePath, h.getEntities());
    	addEntitiesTree(areaEntitiesfilePath, h.getEntities());
    	addEntitiesTree(lineEntitiesfilePath, h.getEntities());
    	addEntitiesTree(pointEntitiesfilePath, h.getEntities());
    	
    	addSectorModifiers(ModifierTypes.One, symbolSet,h.getSectorModifierOnes());
    	addSectorModifiers(ModifierTypes.Two, symbolSet,h.getSectorModifierTwos());
    	
    	return h;
	}
	
    private static void addEntitiesTree(String filePath, List<Entity> enitiesList) {   	

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
        	String row = csvReader.readLine(); // skip CSV header
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
    
    public static boolean isAmplifierApplicable(MilitarySymbol milSym) {
    	return isAmplifierApplicable(milSym, milSym.getSymbolSet());
    }
    
    public static boolean isAmplifierApplicable(MilitarySymbol milSym, SymbolSets set) {
    	Boolean isAmplifierApplicable = false;
    	for(Amplifier a: MilitarySymbolFactory.getApplicableAmplifiers(set)) {
    		if (milSym.getAmplifier().getSidcPart().equalsIgnoreCase(a.getSidcPart())) {
    			if (!(milSym.getAmplifier() instanceof NotApplicableAmplifier))
    				isAmplifierApplicable = true;
    			break;
			}
    	}
    	return isAmplifierApplicable;
    	
    }
    // TODO: Mention in documentation
    public static Amplifier[] getApplicableAmplifiers(SymbolSets symbolSet) {
    	switch(symbolSet) {
    	case LandUnits:
    		return EchelonAmplifiers.values();
    	case LandEquipment:
    		return EquipmentMobilityAmplifiers.values();
    	case SeaSurface:
    	case SeaSubsurface:
    		return NavalTowedArrayAmplifiers.values();
    	default:
    		// Air, Air Missile, Space, Space missile Land Civilian, Land Installation
    		// Control measure, Mine warfare, Activities, All SIGINT, All METOC and cyberspace
    		return NotApplicableAmplifier.values();
    	}    	
    }
}
