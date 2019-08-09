package com.github.yemikudaisi.jmsj;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	public static final String FRAMES_SVG_FOLDER = SVG_FOLDER+"Frames/";
	public static final String HQTFDUMMY_SVG_FOLDER = SVG_FOLDER+"HQTFFD/";
	public static final String AMPLIFIER_SVG_FOLDER = SVG_FOLDER+"Amplifier/";
	public static final String ECHELON_SVG_FOLDER = SVG_FOLDER+"Echelon/";
	public static final String OCA_SVG_FOLDER = SVG_FOLDER+"OCA/";
	
	// Subfolders
	public static final String EXERCISE_FRAMES_FOLDER =FRAMES_SVG_FOLDER+"Exercise/";
	public static final String SIMULATION_FRAMES_FOLDER = FRAMES_SVG_FOLDER+"Sim/";
	
	public static final String APP_MOD_ONE_SVG_FOLDER = "mod1/";
	public static final String APP_MOD_TWO_SVG_FOLDER = "mod2/";
	
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
	
	static Logger logger = Logger.getLogger(SvgFactory.class.getName());
	
	{
		initCsvDomainCodeMapping();
		initSymbolSetToAppendicesFolderMapping();
	}
	
	private static void initSymbolSetToAppendicesFolderMapping() {
		if(ap != null)
			return;
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

    	
    	// Consider symbols sets that use frames belonging to other symbol sets
    	if(c[4]=='1' && c[5]=='1') { // land civilian (SIDC value 11) uses land unit (SIDC value 10)
    		c[4]='1';
    		c[5]='0';
    	}
    	if(c[4]=='5' && c[5]=='0') { // SIGINT Space (SIDC value 50) uses Space (SIDC value 05)
    		c[4]='0';
    		c[5]='5';
    	}
    	
    	if(c[4]=='5' && c[5]=='1') { // SIGINT Air (SIDC value 51) uses Air (SIDC value 01)
    		c[4]='0';
    		c[5]='1';
    	}
    	
    	if((c[4]=='5' && c[5]=='2') || (c[4]=='5' && c[5]=='3')) { // SIGINT Land/Surface (SIDC value 23) uses Sea Surface (SIDC value 30)
    		c[4]='3';
    		c[5]='0';
    	}
    	
    	if(c[4]=='5' && c[5]=='4') { // SIGINT Subsurface - >Sea Subsurface
    		c[4]='3';
    		c[5]='5';
    	}
    	
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
    			fileName = FRAMES_SVG_FOLDER+fileName+".svg";
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
			return OCA_SVG_FOLDER+c[6]+".svg";
		}
		
		// TODO: refactor this code into a private method
		
		// NOTES: Standard Entity 2 (SIDC position 4) of value 2 (Assumed Friend) uses HQTFD SVG symbols for value 3 (Friend) and
		// Standard Entity 2 (SIDC position 4) of value 5 (Suspect/Joker) uses HQTFD symbols for value 6 (Hostile/Faker)
		
		// Consider standard entities with the frame shape  
		if(c[3]=='0') { // if standard entity value pending (SIDC value 5) use resource for hostile (SIDC value 6)
			c[3]='1';
		}		
		if(c[3]=='2') { // if standard entity value assumed friend (SIDC value 3) use resource for friend (SIDC value 3)
			c[3]='3';
		}
		else if(c[3]=='5') { // if standard entity value suspect (SIDC value 5) use resource for hostile (SIDC value 6)
			c[3]='6';
		}
		
		return OCA_SVG_FOLDER+c[2]+c[3]+c[4]+c[5]+c[6]+"2.svg";
		
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
		
		// TODO: refactor this code into a private method
		
		// NOTES: Standard Entity 2 (SIDC position 4) of value 2 (Assumed Friend) uses HQTFD SVG symbols for value 3 (Friend) and
		// Standard Entity 2 (SIDC position 4) of value 5 (Suspect/Joker) uses HQTFD symbols for value 6 (Hostile/Faker)
		
		// Consider standard entities with the frame shape  
		if(c[3]=='0') { // if standard entity value pending (SIDC value 5) use resource for hostile (SIDC value 6)
			c[3]='1';
		}		
		if(c[3]=='2') { // if standard entity value assumed friend (SIDC value 3) use resource for friend (SIDC value 3)
			c[3]='3';
		}
		else if(c[3]=='5') { // if standard entity value suspect (SIDC value 5) use resource for hostile (SIDC value 6)
			c[3]='6';
		}
	
		return HQTFDUMMY_SVG_FOLDER+c[3]+c[4]+c[5]+c[7]+".svg";
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
		
		// Consider standard entities with the frame shape  
		if(c[3]=='0') { // if standard entity value pending (SIDC value 5) use resource for hostile (SIDC value 6)
			c[3]='1';
		}		
		if(c[3]=='2') { // if standard entity value assumed friend (SIDC value 3) use resource for friend (SIDC value 3)
			c[3]='3';
		}
		else if(c[3]=='5') { // if standard entity value suspect (SIDC value 5) use resource for hostile (SIDC value 6)
			c[3]='6';
		}
		
		if(i>2)
			return AMPLIFIER_SVG_FOLDER+c[3]+c[8]+c[9]+".svg";
		else
			return ECHELON_SVG_FOLDER+c[3]+c[8]+c[9]+".svg";
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
	protected static List<String> getEntitySvgResourcePath(MilitarySymbol milSym) {
		initSymbolSetToAppendicesFolderMapping();
		char[] a = milSym.getSidcSetA().toCharArray();
		char[] b = milSym.getSidcSetB().toCharArray();
		List<String> paths = new ArrayList<String>();
		
		// Consider entity sub type '9x' (SIDC position 15-16) which adds an extra symbol 
		// to land units (in addition to the one already generated by the entity and 
		// entity type (SIDC positions 11-12 and 13-14 respectively )
		if( milSym.getSymbolSet() == SymbolSets.LandUnits 
				&& b[4] =='9' ) {
			String entitySubTypePath = SVG_FOLDER+
					ap.get(milSym.getSymbolSet())+
					a[4]+a[5]+"xxxx"+b[4]+b[5]; //10xxxx9x
			paths.add(entitySubTypePath);
		}
		
		SymbolSets set = milSym.getSymbolSet();
		String fileMapping = ap.get(set);
		String fileName = SVG_FOLDER+fileMapping+a[4]+a[5]+b[0]+b[1]+b[2]+b[3]+"00";
		paths.add(fileName);

		// if the expected symbols are not found, give special consideration as
		// possible full frame icons for standard entities in the switch
		List<String> pathsWithExtension = new ArrayList<>();
		for(String p: paths) {
			String suffix = "";
			if(!resourceExists(p+".svg")) {
				switch(milSym.getStandardEntityTwo()) {
				case Unknown:
					suffix = "_0";
					break;
				case AssumedFriend:
					suffix = "_1"; // same as friend
					break;
				case Friend:
					suffix = "_1";
					break;
				case Neutral:
					suffix = "_2";
					break;
				case Suspect:
					suffix = "_3"; // same as hostile
					break;
				case Hostile:
					suffix = "_3";
					break;
				default:
					break;
				}
			}
			pathsWithExtension.add(p+suffix);
			paths = pathsWithExtension;
		}
		
		pathsWithExtension = new ArrayList<>();
		for(String s:paths)
			pathsWithExtension.add(s+".svg");
		
		return pathsWithExtension;
	}
	
	/**
	 * Modifier 1 (Folder: Appendices\XXX\mod1 - Characters: 5): Uses SIDC positions 5-6 and 
	 * 17-18 along with the number 1 at the end.
	 * 
	 * Modifier 2 (Folder: Appendices\XXX\mod2 - Characters: 5): Uses SIDC positions 5-6 and 
	 * 19-20 along with the number 2 at the end.
	 * 
	 * TODO Test
	 * 
	 * @param milSym
	 * @return
	 */
	protected  static List<String> getModifiersSvgResourcePath(MilitarySymbol milSym) {
		String pre = SVG_FOLDER +
				ap.get(milSym.getSymbolSet());
		
		List<String> paths = new ArrayList<String>();
		
		char[] a = milSym.getSidcSetA().toCharArray(); 
		char[] b = milSym.getSidcSetB().toCharArray();
		paths.add( pre + APP_MOD_ONE_SVG_FOLDER + a[4] + a[5] + b[6] + b[7]+ "1.svg");
		paths.add( pre + APP_MOD_TWO_SVG_FOLDER + a[4] + a[5] + b[8] + b[9]+ "2.svg");
		
		return paths;
	}
	
	
	// CSV RESOURCES
	
	
	/**
	 * Uses the symbol set-file name mapping to generate a resource path for
	 * the CSV containing the entities for the supplied symbol set.
	 * 
	 * @param symbolSets The symbols set whose entities' resource path is to be generated
	 * @return The CSV path to the symbol set's entities
	 */
	protected String getEnitiesCsvResourcePath(SymbolSets symbolSets) {
		String path = NAME_DOMAIN_VALUES_FOLDER+
				CODED_DOMAIN_FILE_PREFIX+
				symbolSetToCsvFileNameMap.get(symbolSets)+
				CODED_DOMAIN_ENTITY_FILE_SUFFIX+
				".csv";
		return path;
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
	 * the CSV containing the modifiers for the supplied symbol set based on
	 * the supplied modifier type.
	 * 
	 * @param symbolSet The symbols set whose point modifier resource path is to be generated
	 * @param modifierType The modifier type
	 * @return
	 */
	protected String getModifierCsvResourcePath(SymbolSets symbolSet, ModifierTypes modifierType) {
		String pre = NAME_DOMAIN_VALUES_FOLDER+
				CODED_DOMAIN_FILE_PREFIX+
				symbolSetToCsvFileNameMap.get(symbolSet);
		if(modifierType == ModifierTypes.One) {
			return pre + CODED_DOMAIN_MOD_ONE_FILE_SUFFIX + ".csv";
		}else {
			return pre + CODED_DOMAIN_MOD_TWO_FILE_SUFFIX + ".csv";
		}
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
	
	/**
	 * Checks if a resource exists in the class path
	 * 
	 * @param fileName
	 * @return
	 */
	private static Boolean resourceExists(String fileName) {
		try {
			@SuppressWarnings("unused")
			File f = getResourceFile(fileName);
	    	return true; 
		} catch(Exception e) {
			return false;
		}
	}


}
