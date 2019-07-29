package com.github.yemikudaisi.jmsj;

import java.io.File;
import java.util.HashMap;

import com.github.yemikudaisi.jmsj.symbology.MilitarySymbol;
import com.github.yemikudaisi.jmsj.symbology.SymbolSets;

public class ResourceManager {
	public static final String SVG_FOLDER = "jmsml/svg/MIL_STD_2525D_Symbols/";
	public static final String FRAMES_FOLDER = SVG_FOLDER+"Frames/";
	public static final String EXERCISE_FRAMES_FOLDER =FRAMES_FOLDER+"Exercise/";
	public static final String SIMULATION_FRAMES_FOLDER = FRAMES_FOLDER+"Sim/";
	public static final String HQTFDUMMY_FOLDER = SVG_FOLDER+"HQTFFD/";
	public static final String AMPLIFIER_FOLDER = SVG_FOLDER+"Amplifier/";
	public static final String ECHELON_FOLDER = SVG_FOLDER+"Echelon/";
	public static final String NAME_DOMAIN_VALUES_FOLDER = "jmsml/name_domains_values/";
	
	private HashMap<SymbolSets, String> dc;
	
	private static final String CODED_DOMAIN_FILE_PREFIX = "Coded_Domain_";
	private static final String CODED_DOMAIN_FILE_ENTITY_SUFFIX = "_Entities";
	private static final String CODED_DOMAIN_FILE_MOD_ONE_SUFFIX = "_Modifier_Ones";
	private static final String CODED_DOMAIN_FILE_MOD_TWO_SUFFIX = "_Modifier_Twos";
	private static final String CODED_DOMAIN_FILE_AREA_ENITIES_SUFFIX = "_Area_Entities";
	private static final String CODED_DOMAIN_FILE_LINE_ENITIES_SUFFIX = "_Line_Entities";
	private static final String CODED_DOMAIN_FILE_POINT_ENITIES_SUFFIX = "_Point_Entities";
	
	public ResourceManager() {
		initDomainCodeMapping();
	}
	
	private void initDomainCodeMapping() {
		dc = new HashMap<SymbolSets, String>();
		dc.put(SymbolSets.Air, "Air");
		dc.put(SymbolSets.AirMissile, "Air_Missile");
		dc.put(SymbolSets.Space, "Space");
		dc.put(SymbolSets.SpaceMissile, "Space_Missile");
		dc.put(SymbolSets.LandUnits, "Land_Unit");
		dc.put(SymbolSets.LandEquipment, "Land_Equipment");
		dc.put(SymbolSets.LandInstallation, "Land_Installation");
		dc.put(SymbolSets.ControlMeasure, "Control_Measure");
		dc.put(SymbolSets.SeaSurface, "Sea_Surface");
		dc.put(SymbolSets.SeaSubsurface, "Sea_Subsurface");
		dc.put(SymbolSets.MineWarfare, "Mine_Warfare");
		dc.put(SymbolSets.Activities, "Activities");
		dc.put(SymbolSets.MeteorologicalAtmospheric, "METOC_Atmospheric");
		dc.put(SymbolSets.MeteorologicalOceanographic, "METOC_Oceanographic");
		dc.put(SymbolSets.MeteorologicalSpace, "METOC_Space");
		dc.put(SymbolSets.SignalsIntelligenceSpace, "Space_SIGINT");
		dc.put(SymbolSets.SignalsIntelligenceAir, "Air_SIGINT");
		dc.put(SymbolSets.SignalsIntelligenceLand, "Land_SIGINT");
		dc.put(SymbolSets.SignalsIntelligenceSurface, "Surface_SIGINT");
		dc.put(SymbolSets.SignalsIntelligenceSubsurface, "Subsurface_SIGINT");
		dc.put(SymbolSets.Cyberspace, "");
	}
	
	public String getEnitiesFileName(SymbolSets symbolSets) {
		return NAME_DOMAIN_VALUES_FOLDER+
				CODED_DOMAIN_FILE_PREFIX+
				dc.get(symbolSets)+
				CODED_DOMAIN_FILE_ENTITY_SUFFIX+
				".csv";
	}
	
	public String getModifierOnesFileName(SymbolSets symbolSets) {
		return NAME_DOMAIN_VALUES_FOLDER+
				CODED_DOMAIN_FILE_PREFIX+
				dc.get(symbolSets)+
				CODED_DOMAIN_FILE_MOD_ONE_SUFFIX+
				".csv";
	}
	
	public String getModifierTwosFileName(SymbolSets symbolSets) {
		return NAME_DOMAIN_VALUES_FOLDER+
				CODED_DOMAIN_FILE_PREFIX+
				dc.get(symbolSets)+
				CODED_DOMAIN_FILE_MOD_TWO_SUFFIX+
				".csv";
	}
	
	public String getAreaEnitiesFileName(SymbolSets symbolSets) {
		return NAME_DOMAIN_VALUES_FOLDER+
				CODED_DOMAIN_FILE_PREFIX+
				dc.get(symbolSets)+
				CODED_DOMAIN_FILE_AREA_ENITIES_SUFFIX+
				".csv";
	}
	
	public String getLinesEnitiesFileName(SymbolSets symbolSets) {
		return NAME_DOMAIN_VALUES_FOLDER+
				CODED_DOMAIN_FILE_PREFIX+
				dc.get(symbolSets)+
				CODED_DOMAIN_FILE_LINE_ENITIES_SUFFIX+
				".csv";
	}
	
	public String getPointEnitiesFileName(SymbolSets symbolSets) {
		return NAME_DOMAIN_VALUES_FOLDER+
				CODED_DOMAIN_FILE_PREFIX+
				dc.get(symbolSets)+
				CODED_DOMAIN_FILE_POINT_ENITIES_SUFFIX+
				".csv";
	}
	
	public void domainCodeFileExist() {
		
	}
	

	
	public static String getFrameFileName(MilitarySymbol milSym) {
    	String setA = milSym.getSidcSetA();
    	char[] c = setA.toCharArray();
    	String fileName = c[2]+"_"+c[3]+c[4]+c[5]+"_"+c[6];
    	if(milSym.getSymbolSet() == SymbolSets.LandCivilian
    			|| milSym.getSymbolSet() == SymbolSets.LandEquipment
    			|| milSym.getSymbolSet() == SymbolSets.LandInstallation) {
    		fileName = fileName+"c";    		
    	}
    	
    	switch(milSym.getStandardEntityOne()) {
    		case Exercise:
    			fileName = EXERCISE_FRAMES_FOLDER+fileName+".svg";
    			break;
    		case Simulation:
    			fileName = SIMULATION_FRAMES_FOLDER+fileName+".svg";
    			break;
    		default:
    			fileName = FRAMES_FOLDER+fileName+".svg";
    	}
    	return fileName;    	
    }
	
	public static String getHqTfFileName(MilitarySymbol milSym) {
		char[] c = milSym.getSidcSetA().toCharArray();
		return HQTFDUMMY_FOLDER+c[3]+c[4]+c[5]+c[7]+".svg";
	}
	
	public static String getAmplifierFileName(MilitarySymbol milSym) {
		char[] c = milSym.getSidcSetA().toCharArray();
		int i = Integer.parseInt(String.valueOf(c[8]));
		if(i>2)
			return AMPLIFIER_FOLDER+c[3]+c[8]+c[9]+".svg";
		else
			return ECHELON_FOLDER+c[3]+c[8]+c[9]+".svg";
	}
	
	public static File getFile(String resourcePath) {
		String s = ResourceManager.class.getClassLoader().getResource(resourcePath).getFile();
		return new File(s);
	}

}
