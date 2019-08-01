package com.github.yemikudaisi.jmsj;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.yemikudaisi.jmsj.symbology.Amplifier;
import com.github.yemikudaisi.jmsj.symbology.EchelonAmplifiers;
import com.github.yemikudaisi.jmsj.symbology.NavalTowedArrayAmplifiers;
import com.github.yemikudaisi.jmsj.symbology.SymbolSets;

public class MilitiarySymbolExplorerTests {

	@Test
	public void testLandUnitApplicableAmplifiers() {
		Amplifier[] amps = MilitarySymbolFactory.getApplicableAmplifiers(SymbolSets.LandUnits);
		Amplifier amp = amps[0];
		assertTrue(
				"Echelon is the applicable amplifier for land units", 
				amp instanceof EchelonAmplifiers);
	}
	
	@Test
	public void testLandEquipmentApplicableAmplifiers() {
		Amplifier[] amps = MilitarySymbolFactory.getApplicableAmplifiers(SymbolSets.LandEquipment);
		Amplifier amp = amps[0];
		assertTrue(
				"Equipment mobility is the applicable amplifier for land equipment", 
				amp instanceof EchelonAmplifiers);
	}
	
	@Test
	public void testSeaSurfaceApplicableAmplifiers() {
		Amplifier[] amps = MilitarySymbolFactory.getApplicableAmplifiers(SymbolSets.SeaSurface);
		Amplifier amp = amps[0];
		assertTrue(
				"Naval towed array is the applicable for sea surface", 
				amp instanceof NavalTowedArrayAmplifiers);
	}
	
	@Test
	public void testSeaSubsurfaceApplicableAmplifiers() {
		Amplifier[] amps = MilitarySymbolFactory.getApplicableAmplifiers(SymbolSets.SeaSubsurface);
		Amplifier amp = amps[0];
		assertTrue(
				"Naval towed array is the applicable for sea sub surface", 
				amp instanceof NavalTowedArrayAmplifiers);
	}

}
