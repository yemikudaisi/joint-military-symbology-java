package com.github.yemikudaisi.jmsj;

import com.github.yemikudaisi.jmsj.symbology.MilitarySymbol;
import com.github.yemikudaisi.jmsj.symbology.SymbolSets;

public class JmsmlFileSystem {
	public static final String SVG_FOLDER = "jmsml/svg/MIL_STD_2525D_Symbols/";
	public static final String FRAMES_FOLDER = SVG_FOLDER+"Frames/";
	public static final String EXERCISE_FRAMES_FOLDER =FRAMES_FOLDER+"Exercise/";
	public static final String SIMULATION_FRAMES_FOLDER = FRAMES_FOLDER+"Sim/";
	public static final String HQTFDUMMY_FOLDER = SVG_FOLDER+"HQTFFD/";
	public static final String AMPLIFIER_FOLDER = SVG_FOLDER+"Amplifier/";
	public static final String ECHELON_FOLDER = SVG_FOLDER+"Echelon/";
	
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

}
