package com.github.yemikudaisi.jmsj.symbology;

public class Symbol
{
	String version = "10";
	StandardEntityOnes standardEntityOne = StandardEntityOnes.Reality;
	StandardEntityTwos standardEntityTwo = StandardEntityTwos.Friend;
	SymbolSets symbolSet = SymbolSets.LandUnits;
	Status status = Status.Present;
	HQTFDummy hqTFDummy = HQTFDummy.NotApplicable;
	Amplifier amplifier = BrigadeBelowEchelonAmplifier.Company;
	String sidcSetB = "";
	
    public Symbol() { }
    public Symbol(String version, StandardEntityOnes standardEntityOne, StandardEntityTwos standardEntityTwo, SymbolSets symbolSet, Status status, HQTFDummy hQTFDummy, Amplifier amplifier)
    {
        this.version = version;
        this.standardEntityOne = standardEntityOne;
        this.standardEntityTwo = standardEntityTwo;
        this.symbolSet = symbolSet;
        this.status = status;
        this.hqTFDummy = hQTFDummy;
        this.amplifier = amplifier;   
    }

    public String getSidcSetA() { 
    	return version +
            standardEntityOne.getSidcPart() +
            standardEntityTwo.getSidcPart() +
            symbolSet.getSidcPart() +
            status.getSidcPart() +
            hqTFDummy.getSidcPart() +
            amplifier.getSidcPart(); 
    	}

    @Override
    public String toString()
    {
        return getSidcSetA()+sidcSetB;
    }
}
