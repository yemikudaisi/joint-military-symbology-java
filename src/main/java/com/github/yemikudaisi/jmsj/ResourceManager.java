package com.github.yemikudaisi.jmsj;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

import com.github.yemikudaisi.jmsj.symbology.MilitarySymbol;
import com.github.yemikudaisi.jmsj.symbology.SymbolSets;

/**
 * Helper Class to manage SVG and CSV resources produced by
 * ESRI's Joint Military Symbology Markup Language (JMSML)
 * https://github.com/Esri/joint-military-symbology-xml
 * @author HA.Kudaisi
 *
 */
public class ResourceManager {
	public static final String SVG_FOLDER = "jmsml/svg/MIL_STD_2525D_Symbols/";
	public static final String FRAMES_FOLDER = SVG_FOLDER+"Frames/";
	public static final String EXERCISE_FRAMES_FOLDER =FRAMES_FOLDER+"Exercise/";
	public static final String SIMULATION_FRAMES_FOLDER = FRAMES_FOLDER+"Sim/";
	public static final String HQTFDUMMY_FOLDER = SVG_FOLDER+"HQTFFD/";
	public static final String AMPLIFIER_FOLDER = SVG_FOLDER+"Amplifier/";
	public static final String ECHELON_FOLDER = SVG_FOLDER+"Echelon/";
	public static final String OCA_FOLDER = SVG_FOLDER+"OCA/";
	public static final String NAME_DOMAIN_VALUES_FOLDER = "jmsml/name_domains_values/";
	
	private HashMap<SymbolSets, String> symbolSetFileNameMap;
	
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
	
	/**
	 * Initializes the Symbol sets to file name mapping based
	 * on CSV file supplied by JMSML
	 */
	private void initDomainCodeMapping() {
		symbolSetFileNameMap = new HashMap<SymbolSets, String>();
		symbolSetFileNameMap.put(SymbolSets.Air, "Air");
		symbolSetFileNameMap.put(SymbolSets.AirMissile, "Air_Missile");
		symbolSetFileNameMap.put(SymbolSets.Space, "Space");
		symbolSetFileNameMap.put(SymbolSets.SpaceMissile, "Space_Missile");
		symbolSetFileNameMap.put(SymbolSets.LandUnits, "Land_Unit");
		symbolSetFileNameMap.put(SymbolSets.LandEquipment, "Land_Equipment");
		symbolSetFileNameMap.put(SymbolSets.LandInstallation, "Land_Installation");
		symbolSetFileNameMap.put(SymbolSets.ControlMeasure, "Control_Measure");
		symbolSetFileNameMap.put(SymbolSets.SeaSurface, "Sea_Surface");
		symbolSetFileNameMap.put(SymbolSets.SeaSubsurface, "Sea_Subsurface");
		symbolSetFileNameMap.put(SymbolSets.MineWarfare, "Mine_Warfare");
		symbolSetFileNameMap.put(SymbolSets.Activities, "Activities");
		symbolSetFileNameMap.put(SymbolSets.MeteorologicalAtmospheric, "METOC_Atmospheric");
		symbolSetFileNameMap.put(SymbolSets.MeteorologicalOceanographic, "METOC_Oceanographic");
		symbolSetFileNameMap.put(SymbolSets.MeteorologicalSpace, "METOC_Space");
		symbolSetFileNameMap.put(SymbolSets.SignalsIntelligenceSpace, "Space_SIGINT");
		symbolSetFileNameMap.put(SymbolSets.SignalsIntelligenceAir, "Air_SIGINT");
		symbolSetFileNameMap.put(SymbolSets.SignalsIntelligenceLand, "Land_SIGINT");
		symbolSetFileNameMap.put(SymbolSets.SignalsIntelligenceSurface, "Surface_SIGINT");
		symbolSetFileNameMap.put(SymbolSets.SignalsIntelligenceSubsurface, "Subsurface_SIGINT");
		symbolSetFileNameMap.put(SymbolSets.Cyberspace, "");
	}
	
	/**
	 * Uses the symbol set-file name mapping to generate a resource path for
	 * the CSV containing the entities for the supplied symbol set.
	 * @param symbolSets The symbols set whose entities' resource path is to be generated
	 * @return The CSV path to the symbol set's entities
	 */
	protected String getEnitiesCsvResourcePath(SymbolSets symbolSets) {
		return NAME_DOMAIN_VALUES_FOLDER+
				CODED_DOMAIN_FILE_PREFIX+
				symbolSetFileNameMap.get(symbolSets)+
				CODED_DOMAIN_FILE_ENTITY_SUFFIX+
				".csv";
	}
	
	/**
	 * Uses the symbol set-file name mapping to generate a resource path for
	 * the CSV containing the sector modifier ones for the supplied symbol set.
	 * @param symbolSets The symbols set whose modifiers' resource path is to be generated
	 * @return The CSV path to the symbol set's sector modifier ones
	 */
	protected String getSectorModifierOnesCsvResourcePath(SymbolSets symbolSets) {
		return NAME_DOMAIN_VALUES_FOLDER+
				CODED_DOMAIN_FILE_PREFIX+
				symbolSetFileNameMap.get(symbolSets)+
				CODED_DOMAIN_FILE_MOD_ONE_SUFFIX+
				".csv";
	}
	
	/**
	 * Uses the symbol set-file name mapping to generate a resource path for
	 * the CSV containing the sector modifier twos for the supplied symbol set.
	 * @param symbolSets The symbols set whose modifiers' resource path is to be generated
	 * @return The CSV path to the symbol set's sector modifier twos
	 */
	protected String getSectorModifierTwosCsvResourcePath(SymbolSets symbolSets) {
		return NAME_DOMAIN_VALUES_FOLDER+
				CODED_DOMAIN_FILE_PREFIX+
				symbolSetFileNameMap.get(symbolSets)+
				CODED_DOMAIN_FILE_MOD_TWO_SUFFIX+
				".csv";
	}
	
	/**
	 * Uses the symbol set-file name mapping to generate a resource path for
	 * the CSV containing the special area entities for the supplied symbol set.
	 * @param symbolSets The symbols set whose area entities' resource path is to be generated
	 * Note: Peculiar to METOC atmospheric/Oceanographic/space and Cyberspace
	 * @return The CSV path to the symbol set's area entities
	 */
	protected String getAreaEnitiesCsvResourcePath(SymbolSets symbolSets) {
		return NAME_DOMAIN_VALUES_FOLDER+
				CODED_DOMAIN_FILE_PREFIX+
				symbolSetFileNameMap.get(symbolSets)+
				CODED_DOMAIN_FILE_AREA_ENITIES_SUFFIX+
				".csv";
	}
	
	/**
	 * Uses the symbol set-file name mapping to generate a resource path for
	 * the CSV containing the special line entities for the supplied symbol set.
	 * @param symbolSets The symbols set whose line entities' resource path is to be generated
	 * Note: Peculiar to METOC atmospheric/Oceanographic/space and Cyberspace
	 * @return The CSV path to the symbol set's line entities
	 */
	protected String getLineEnitiesCsvResourcePath(SymbolSets symbolSets) {
		return NAME_DOMAIN_VALUES_FOLDER+
				CODED_DOMAIN_FILE_PREFIX+
				symbolSetFileNameMap.get(symbolSets)+
				CODED_DOMAIN_FILE_LINE_ENITIES_SUFFIX+
				".csv";
	}
	
	/**
	 * Uses the symbol set-file name mapping to generate a resource path for
	 * the CSV containing the point entities for the supplied symbol set.
	 * @param symbolSets The symbols set whose point entities' resource path is to be generated
	 * Note: Peculiar to METOC atmospheric/Oceanographic/space and Cyberspace
	 * @return The CSV path to the symbol set's point entities
	 */
	protected String getPointEnitiesCsvResourcePath(SymbolSets symbolSets) {
		return NAME_DOMAIN_VALUES_FOLDER+
				CODED_DOMAIN_FILE_PREFIX+
				symbolSetFileNameMap.get(symbolSets)+
				CODED_DOMAIN_FILE_POINT_ENITIES_SUFFIX+
				".csv";
	}
	
	/**
	 * Uses SIDC positions 3-7 to generate a filename (in "Frames" folder) with
	 * an underscore between the first digit in the name and the last digit in the name. Purple filled frames for Civilian 
	 * units, equipment, and installations have a ‘c’ at the end of the file name.
	 * @param milSym
	 * @return
	 */
	protected static String getFrameSvgResourcePath(MilitarySymbol milSym) {
		Boolean isCivEqptInst = (milSym.getSymbolSet() == SymbolSets.LandCivilian
    			|| milSym.getSymbolSet() == SymbolSets.LandEquipment
    			|| milSym.getSymbolSet() == SymbolSets.LandInstallation)?true:false;
    	String setA = milSym.getSidcSetA();
    	char[] c = setA.toCharArray();
    	String fileName = c[2]+"_"+c[3]+c[4]+c[5]+"_"+(c[6]=='1'?'1':'0');
    	if(isCivEqptInst) {
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
    	
    	if(!resourceExists(fileName)) {
			StringBuilder b = new StringBuilder(fileName);
			if(isCivEqptInst)
				b.setCharAt(b.length()-6, '0');
			else
				b.setCharAt(b.length()-5, '0');
    		fileName = b.toString();
		}
    	return fileName;
    }
	
	/**
	 * Check if a resource exists given it path
	 * @param fileName
	 * @return
	 */
	private static Boolean resourceExists(String fileName) {
		URL resource = ResourceManager.class.getResource(fileName);
    	if(resource==null)
    		return false;
    	return true;  
	}
	
	/**
	 * <p>Uses SIDC positions 4-6 and position 8 (4 characters) to generate a file name
	 * in HQTFFD folder.</p>
	 * 
	 * <p>Note that for Standard Entity 2 (SIDC position 4) of value '2' (Assumed Friend) 
	 * uses HQTFD SVG symbols for value '3' (Friend) and Standard Entity 2 
	 * (SIDC position 4) of value '5' (Suspect/Joker) uses HQTFD symbols for 
	 * value '6' (Hostile/Faker) </p>
	 * 
	 * @param milSym
	 * @return a string representing the resource path for the symbols HQTFDummy SVG
	 */
	protected static String getHqTfDummySvgResourcePath(MilitarySymbol milSym) {
		char[] c = milSym.getSidcSetA().toCharArray();
		// NOTES: Standard Entity 2 (SIDC position 4) of value 2 (Assumed Friend) uses HQTFD SVG symbols for value 3 (Friend) and
		// Standard Entity 2 (SIDC position 4) of value 5 (Suspect/Joker) uses HQTFD symbols for value 6 (Hostile/Faker)
		if(c[3]=='2') {
			c[3]='3';
		}
		else if(c[3]=='5') {
			c[3]='6';
		}
		
		return HQTFDUMMY_FOLDER+c[3]+c[4]+c[5]+c[7]+".svg";
	}
	
	/**
	 * <p>Generates the SVG resource path  for symbol Amplifier (Mobility/Towed Array)
	 * in Folder: Amplifier. Uses 3 characters SIDC positions 4 and 9-10.</p>
	 * 
	 * <p>Note that for Standard Entity 2 (SIDC position 4) of value '2' (Assumed Friend) 
	 * uses HQTFD SVG symbols for value '3' (Friend) and Standard Entity 2 
	 * (SIDC position 4) of value '5' (Suspect/Joker) uses HQTFD symbols for 
	 * value '6' (Hostile/Faker) </p>
	 * 
	 * @param milSym
	 * @return
	 */
	protected static String getAmplifierSvgResourcePath(MilitarySymbol milSym) {
		char[] c = milSym.getSidcSetA().toCharArray();
		int i = Integer.parseInt(String.valueOf(c[8]));
		
		if(c[3]=='2') {
			c[3]='3';
		}
		else if(c[3]=='5') {
			c[3]='6';
		}
		
		if(i>2)
			return AMPLIFIER_FOLDER+c[3]+c[8]+c[9]+".svg";
		else
			return ECHELON_FOLDER+c[3]+c[8]+c[9]+".svg";
	}
	
	/**
	 * <p>Uses 1 character from SIDC position 7 to generate a SVG resource for
	 * the default version of (overlaid / or X) Status/Operational Condition 
	 * Amplifier (OCA).</p>
	 * 
	 * <p>Note that for Standard Entity 2 (SIDC position 4) of value '2' (Assumed Friend) 
	 * uses HQTFD SVG symbols for value '3' (Friend) and Standard Entity 2 
	 * (SIDC position 4) of value '5' (Suspect/Joker) uses HQTFD symbols for 
	 * value '6' (Hostile/Faker) </p>
	 * 
	 * @param milSym
	 * @return
	 */
	protected static String getStatusSvgResourcePath(MilitarySymbol milSym){
		char[] c = milSym.getSidcSetA().toCharArray();
		
		return OCA_FOLDER+c[6]+".svg";
		
	}
	
	protected static File getResourceFile(String resourcePath) {
		String s = ResourceManager.class.getClassLoader().getResource(resourcePath).getFile();
		return new File(s);
	}


}
