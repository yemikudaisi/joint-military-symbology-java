package com.github.yemikudaisi.jmsj;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

import com.github.yemikudaisi.jmsj.symbology.MilitarySymbol;
import com.github.yemikudaisi.jmsj.symbology.ModifierTypes;
import com.github.yemikudaisi.jmsj.symbology.StatusAmplifierModes;
import com.github.yemikudaisi.jmsj.symbology.SymbolSets;

/**
 * Helper Class to manage SVG and CSV resources produced by
 * ESRI's Joint Military Symbology Markup Language (JMSML)
 * https://github.com/Esri/joint-military-symbology-xml
 * @author Yemi Kudaisi
 *
 */
public class ResourceManager {
	
	// Base folders relative to jmsml folder
	public static final String SVG_FOLDER = "jmsml/svg/MIL_STD_2525D_Symbols/";
	public static final String NAME_DOMAIN_VALUES_FOLDER = "jmsml/name_domains_values/";
	
	// Folders
	public static final String FRAMES_FOLDER = SVG_FOLDER+"Frames/";
	public static final String HQTFDUMMY_FOLDER = SVG_FOLDER+"HQTFFD/";
	public static final String AMPLIFIER_FOLDER = SVG_FOLDER+"Amplifier/";
	public static final String ECHELON_FOLDER = SVG_FOLDER+"Echelon/";
	public static final String OCA_FOLDER = SVG_FOLDER+"OCA/";
	
	// Subfolders
	public static final String EXERCISE_FRAMES_FOLDER =FRAMES_FOLDER+"Exercise/";
	public static final String SIMULATION_FRAMES_FOLDER = FRAMES_FOLDER+"Sim/";
	
	// IO Mappings
	private static HashMap<SymbolSets, String> symbolSetToCsvFileNameMap;
	private static HashMap<SymbolSets, String> ap;
	
	// Entity/Types/Subtypes and Sector modifier mappings
	private static final String CODED_DOMAIN_FILE_PREFIX = "Coded_Domain_";
	private static final String CODED_DOMAIN_ENTITY_FILE_SUFFIX = "_Entities";
	private static final String CODED_DOMAIN_AREA_ENITIES_FILE_SUFFIX = "_Area_Entities";
	private static final String CODED_DOMAIN_LINE_ENITIES_FILE_SUFFIX = "_Line_Entities";
	private static final String CODED_DOMAIN_POINT_ENITIES_FILE_SUFFIX = "_Point_Entities";
	private static final String CODED_DOMAIN_MOD_ONE_FILE_SUFFIX = "_Modifier_Ones";
	private static final String CODED_DOMAIN_MOD_TWO_FILE_SUFFIX = "_Modifier_Twos";
	
	{
		initCsvDomainCodeMapping();
		initSymbolSetToAppendicesFolderMapping();
	}
	
	private static void initSymbolSetToAppendicesFolderMapping() {
		ap = new HashMap<SymbolSets, String>();
		ap.put(SymbolSets.Air, "Appendices/Air/");
		ap.put(SymbolSets.AirMissile, "Appendices/Air/");
		ap.put(SymbolSets.Space, "Appendices/Space/");
		ap.put(SymbolSets.SpaceMissile, "Space_Missile");
		ap.put(SymbolSets.LandUnits, "Appendices/Land/");
		ap.put(SymbolSets.LandCivilian, "Appendices/Land/");
		ap.put(SymbolSets.LandEquipment, "Appendices/Land/");
		ap.put(SymbolSets.LandInstallation, "Appendices/Land/");
		ap.put(SymbolSets.ControlMeasure, "Appendices/ControlMeasures/");
		ap.put(SymbolSets.SeaSurface, "Appendices/SeaSurface/");
		ap.put(SymbolSets.SeaSubsurface, "Appendices/SeaSubsurface/");
		ap.put(SymbolSets.MineWarfare, "Appendices/SeaSubsurface/");
		ap.put(SymbolSets.Activities, "Appendices/Activities/");
		ap.put(SymbolSets.MeteorologicalAtmospheric, "Appendices/METOC/Atmospheric/");
		ap.put(SymbolSets.MeteorologicalOceanographic, "Appendices/METOC/Oceanographic/");
		ap.put(SymbolSets.SignalsIntelligenceSpace, "Appendices/SigInt/");
		ap.put(SymbolSets.SignalsIntelligenceAir, "Appendices/SigInt/");
		ap.put(SymbolSets.SignalsIntelligenceLand, "Appendices/SigInt/");
		ap.put(SymbolSets.SignalsIntelligenceSurface, "Appendices/SigInt/");
		ap.put(SymbolSets.SignalsIntelligenceSubsurface, "Appendices/SigInt/");
		
		// EXEMPTIONS
		// METOC Space: No icon is associated with this entity. It is for hierarchal purposes only.
		// Cyberspace There is no symbol associated with this entity.
	}
	
	/**
	 * Initializes the Symbol sets to file name mapping based
	 * on CSV file supplied by JMSML
	 */
	private void initCsvDomainCodeMapping() {
		symbolSetToCsvFileNameMap = new HashMap<SymbolSets, String>();
		symbolSetToCsvFileNameMap.put(SymbolSets.Air, "Air");
		symbolSetToCsvFileNameMap.put(SymbolSets.AirMissile, "Air_Missile");
		symbolSetToCsvFileNameMap.put(SymbolSets.Space, "Space");
		symbolSetToCsvFileNameMap.put(SymbolSets.SpaceMissile, "Space_Missile");
		symbolSetToCsvFileNameMap.put(SymbolSets.LandUnits, "Land_Unit");
		symbolSetToCsvFileNameMap.put(SymbolSets.LandCivilian, "Land_Unit_Civilian");
		symbolSetToCsvFileNameMap.put(SymbolSets.LandEquipment, "Land_Equipment");
		symbolSetToCsvFileNameMap.put(SymbolSets.LandInstallation, "Land_Installation");
		symbolSetToCsvFileNameMap.put(SymbolSets.ControlMeasure, "Control_Measure");
		symbolSetToCsvFileNameMap.put(SymbolSets.SeaSurface, "Sea_Surface");
		symbolSetToCsvFileNameMap.put(SymbolSets.SeaSubsurface, "Sea_Subsurface");
		symbolSetToCsvFileNameMap.put(SymbolSets.MineWarfare, "Mine_Warfare");
		symbolSetToCsvFileNameMap.put(SymbolSets.Activities, "Activities");
		symbolSetToCsvFileNameMap.put(SymbolSets.MeteorologicalAtmospheric, "METOC_Atmospheric");
		symbolSetToCsvFileNameMap.put(SymbolSets.MeteorologicalOceanographic, "METOC_Oceanographic");
		symbolSetToCsvFileNameMap.put(SymbolSets.MeteorologicalSpace, "METOC_Space");
		symbolSetToCsvFileNameMap.put(SymbolSets.SignalsIntelligenceSpace, "Space_SIGINT");
		symbolSetToCsvFileNameMap.put(SymbolSets.SignalsIntelligenceAir, "Air_SIGINT");
		symbolSetToCsvFileNameMap.put(SymbolSets.SignalsIntelligenceLand, "Land_SIGINT");
		symbolSetToCsvFileNameMap.put(SymbolSets.SignalsIntelligenceSurface, "Surface_SIGINT");
		symbolSetToCsvFileNameMap.put(SymbolSets.SignalsIntelligenceSubsurface, "Subsurface_SIGINT");
		symbolSetToCsvFileNameMap.put(SymbolSets.Cyberspace, "Cyberspace");
	}
	
	/**
	 * Uses the symbol set-file name mapping to generate a resource path for
	 * the CSV containing the entities for the supplied symbol set.
	 * 
	 * @param symbolSets The symbols set whose entities' resource path is to be generated
	 * @return The CSV path to the symbol set's entities
	 */
	protected String getEnitiesCsvResourcePath(SymbolSets symbolSets) {
		return NAME_DOMAIN_VALUES_FOLDER+
				CODED_DOMAIN_FILE_PREFIX+
				symbolSetToCsvFileNameMap.get(symbolSets)+
				CODED_DOMAIN_ENTITY_FILE_SUFFIX+
				".csv";
	}
	
	/**
	 * Uses the symbol set-file name mapping to generate a resource path for
	 * the CSV containing the sector modifier ones for the supplied symbol set.
	 * 
	 * @param symbolSets The symbols set whose modifiers' resource path is to be generated
	 * @return The CSV path to the symbol set's sector modifier ones
	 */
	protected String getSectorModifierOnesCsvResourcePath(SymbolSets symbolSets) {
		return NAME_DOMAIN_VALUES_FOLDER+
				CODED_DOMAIN_FILE_PREFIX+
				symbolSetToCsvFileNameMap.get(symbolSets)+
				CODED_DOMAIN_MOD_ONE_FILE_SUFFIX+
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
				symbolSetToCsvFileNameMap.get(symbolSets)+
				CODED_DOMAIN_MOD_TWO_FILE_SUFFIX+
				".csv";
	}
	
	/**
	 * Uses the symbol set-file name mapping to generate a resource path for
	 * the CSV containing the special area entities for the supplied symbol set.
	 * 
	 * @param symbolSets The symbols set whose area entities' resource path is to be generated
	 * Note: Peculiar to METOC atmospheric/Oceanographic/space and Cyberspace
	 * @return The CSV path to the symbol set's area entities
	 */
	protected String getAreaEnitiesCsvResourcePath(SymbolSets symbolSets) {
		return NAME_DOMAIN_VALUES_FOLDER+
				CODED_DOMAIN_FILE_PREFIX+
				symbolSetToCsvFileNameMap.get(symbolSets)+
				CODED_DOMAIN_AREA_ENITIES_FILE_SUFFIX+
				".csv";
	}
	
	/**
	 * Uses the symbol set-file name mapping to generate a resource path for
	 * the CSV containing the special line entities for the supplied symbol set.
	 * 
	 * @param symbolSets The symbols set whose line entities' resource path is to be generated
	 * Note: Peculiar to METOC atmospheric/Oceanographic/space and Cyberspace
	 * @return The CSV path to the symbol set's line entities
	 */
	protected String getLineEnitiesCsvResourcePath(SymbolSets symbolSets) {
		return NAME_DOMAIN_VALUES_FOLDER+
				CODED_DOMAIN_FILE_PREFIX+
				symbolSetToCsvFileNameMap.get(symbolSets)+
				CODED_DOMAIN_LINE_ENITIES_FILE_SUFFIX+
				".csv";
	}
	
	/**
	 * Uses the symbol set-file name mapping to generate a resource path for
	 * the CSV containing the point entities for the supplied symbol set.
	 * 
	 * @param symbolSets The symbols set whose point entities' resource path is to be generated
	 * Note: Peculiar to METOC atmospheric/Oceanographic/space and Cyberspace
	 * @return The CSV path to the symbol set's point entities
	 */
	protected String getPointEnitiesCsvResourcePath(SymbolSets symbolSets) {
		return NAME_DOMAIN_VALUES_FOLDER+
				CODED_DOMAIN_FILE_PREFIX+
				symbolSetToCsvFileNameMap.get(symbolSets)+
				CODED_DOMAIN_POINT_ENITIES_FILE_SUFFIX+
				".csv";
	}
	
	
	/**
	 * Uses the symbol set-file name mapping to generate a resource path for
	 * the CSV containing the modifiers for the supplied symbol set based on
	 * the supplied modifier type.
	 * 
	 * @param symbolSet The symbols set whose point modifier resource path is to be generated
	 * @param modifierType The modifier type
	 * @return
	 */
	protected String getModifierCsvResourcePath(SymbolSets symbolSet, ModifierTypes modifierType) {
		if(modifierType == ModifierTypes.One) {
			return NAME_DOMAIN_VALUES_FOLDER+
					CODED_DOMAIN_FILE_PREFIX+
					symbolSetToCsvFileNameMap.get(symbolSet)+
					CODED_DOMAIN_MOD_ONE_FILE_SUFFIX+
					".csv";
		}else {
			return NAME_DOMAIN_VALUES_FOLDER+
					CODED_DOMAIN_FILE_PREFIX+
					symbolSetToCsvFileNameMap.get(symbolSet)+
					CODED_DOMAIN_MOD_TWO_FILE_SUFFIX+
					".csv";
		}
	}
	
	/**
	 * Main Icon (Folder: Appendices\XXX - Characters: 8): Uses SIDC positions 5-6 and 11-16.
	 * Note: For full-frame main icons (main icons that touch the frame), there is an additional 
	 * suffix depending on the frame that the icon must touch:
	 * _0 = Unknown
	 * _1 = Friend
	 * _2 = Neutral
	 * _3 = Hostile
	 * @param milSym
	 * @return
	 */
	protected static String getEntitySvgResourcePath(MilitarySymbol milSym) {
		char[] a = milSym.getSidcSetA().toCharArray();
		char[] b = milSym.getSidcSetB().toCharArray();
		String fileName = SVG_FOLDER+
				ap.get(milSym.getSymbolSet())+
				a[4]+a[5]+b[0]+b[1]+b[2]+b[3]+b[4]+b[5];
		if(!resourceExists(fileName+".svg")) {
			switch(milSym.getStandardEntityTwo()) {
			case Unknown:
				fileName += "_0";
				break;
			case AssumedFriend:
				fileName += "_1";
				break;
			case Friend:
				fileName += "_1";
				break;
			case Neutral:
				fileName += "_2";
				break;
			case Suspect:
				fileName += "_3";
				break;
			case Hostile:
				fileName += "_3";
				break;
			default:
				break;
			}
		}
		return fileName+".svg";
	}
	
	/**
	 * Uses SIDC positions 3-7 to generate a filename (in "Frames" folder) with
	 * an underscore between the first digit in the name and the last digit in the name. Purple filled frames for Civilian 
	 * units, equipment, and installations have a ‘c’ at the end of the file name.
	 * @param milSym The military symbols who's frame SVG resource path is to be generated
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
	 * TODO: Add test cases for SIDC 4 when '2' or '5'
	 * <p>Note that for Standard Entity 2 (SIDC position 4) of value '2' (Assumed Friend) 
	 * uses HQTFD SVG symbols for value '3' (Friend) and Standard Entity 2 
	 * (SIDC position 4) of value '5' (Suspect/Joker) uses HQTFD symbols for 
	 * value '6' (Hostile/Faker) </p>
	 * 
	 * @param milSym The military symbols who's HQTFDummy SVG resource path is to be generated
	 * @return A string representing the generated resource path for the symbol's HQTFDummy SVG
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
	 * TODO: Add test cases for SIDC 4 when '2' or '5'
	 * <p>Note that for Standard Entity 2 (SIDC position 4) of value '2' (Assumed Friend) 
	 * uses HQTFD SVG symbols for value '3' (Friend) and Standard Entity 2 
	 * (SIDC position 4) of value '5' (Suspect/Joker) uses HQTFD symbols for 
	 * value '6' (Hostile/Faker) </p>
	 * 
	 * @param milSym The military symbols who's Amplifier SVG resource path is to be generated
	 * @return A string representing the generated resource path for the symbol's Amplifier SVG
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
	 * Amplifier (OCA) when the symbols amplifier mode is set to Default.</p>
	 * 
	 * <p>When the symbols amplifier mode is set to Alternate it produces an 
	 * optional version (colored bars) which Uses SIDC positions 3-7 along 
	 * with an additional value of 2 at the end</p>
	 * 
	 * @param milSym The military symbols who's status SVG resource path is to be generated
	 * @return A string representing the generated resource path for the symbol's 
	 * Status\Operation Condition Amplifier SVG
	 */
	protected static String getStatusSvgResourcePath(MilitarySymbol milSym){
		char[] c = milSym.getSidcSetA().toCharArray();
		if(milSym.getStatusAmplifierMode() == StatusAmplifierModes.Default) {
			return OCA_FOLDER+c[6]+".svg";
		}
		if(c[3]=='2') {
			c[3]='3';
		}
		else if(c[3]=='5') {
			c[3]='6';
		}
		return OCA_FOLDER+c[2]+c[3]+c[4]+c[5]+c[6]+"2.svg";
		
	}
	
	/**
	 * Get's the file at the supplied resource path
	 * 
	 * @param resourcePath The path to a resource file
	 * @return The file the supplied path was referencing
	 */
	protected static File getResourceFile(String resourcePath) {
		String s = ResourceManager.class.getClassLoader().getResource(resourcePath).getFile();
		return new File(s);
	}


}
