package com.github.yemikudaisi.jmsj;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.yemikudaisi.jmsj.symbology.EquipmentMobilityOnLandAmplifier;
import com.github.yemikudaisi.jmsj.symbology.HQTFDummy;
import com.github.yemikudaisi.jmsj.symbology.MilitarySymbol;
import com.github.yemikudaisi.jmsj.symbology.SymbolSets;

public class JmsmlFileSystemTests {
	MilitarySymbol milSym;
	
	public JmsmlFileSystemTests() {
		milSym = new MilitarySymbol(); // Set A => 1003100015
	}
	
	@Test
	public void frameSVGFileTest() {
		//Mapping: X_XXX_X.svg => 3_456_7.svg (SIDC)		
		String fileName = JmsmlFileSystem.getFrameFileName(milSym);
		assertEquals("jmsml/svg/MIL_STD_2525D_Symbols/Frames/0_310_0.svg", fileName);
		
	}
	
	@Test
	public void frameCivilianSVGFileTest() {
		//Mapping: X_XXX_Xc.svg => 3_456_7c.svg (SIDC)
		milSym.setSymbolSet(SymbolSets.LandCivilian);
		String fileName = JmsmlFileSystem.getFrameFileName(milSym);
		assertEquals(JmsmlFileSystem.FRAMES_FOLDER+"0_311_0c.svg", fileName);
	}
	
	@Test
	public void frameEquipmentSVGFileTest() {
		//Mapping: X_XXX_Xc.svg => 3_456_7c.svg (SIDC)
		milSym.setSymbolSet(SymbolSets.LandEquipment);
		String fileName = JmsmlFileSystem.getFrameFileName(milSym);
		assertEquals(JmsmlFileSystem.FRAMES_FOLDER+"0_315_0c.svg",fileName);
	}
	
	@Test
	public void frameInstallationSVGFileTest() {
		//Mapping: X_XXX_Xc.svg => 3_456_7c.svg (SIDC)
		milSym.setSymbolSet(SymbolSets.LandInstallation);
		String fileName = JmsmlFileSystem.getFrameFileName(milSym);
		assertEquals(JmsmlFileSystem.FRAMES_FOLDER+"0_320_0c.svg",fileName);
	}
	
	@Test
	public void amplifierSVGFileTest() {
		//Mapping: Amplifier/XXXc.svg => Amplifier/49{10}.svg (SIDC)
		milSym.setAmplifier(EquipmentMobilityOnLandAmplifier.PackAnimals);
		String fileName = JmsmlFileSystem.getAmplifierFileName(milSym);
		assertEquals(JmsmlFileSystem.AMPLIFIER_FOLDER+"337.svg",fileName);
	}
	
	@Test
	public void echelonSVGFileTest() {
		//Mapping: Echelon/XXX.svg => Amplifier/49{10}.svg (SIDC)
		String fileName = JmsmlFileSystem.getAmplifierFileName(milSym);
		assertEquals(JmsmlFileSystem.ECHELON_FOLDER+"315.svg",fileName);
	}
	
	@Test
	public void hqTFDummySVGFileTest() {
		//Mapping: Echelon/XXXX.svg => Amplifier/4568.svg (SIDC)
		milSym.setHqTFDummy(HQTFDummy.FientDummyHeadquarters);
		String fileName = JmsmlFileSystem.getHqTfFileName(milSym);
		assertEquals(JmsmlFileSystem.HQTFDUMMY_FOLDER+"3103.svg",fileName);		
	}
}
