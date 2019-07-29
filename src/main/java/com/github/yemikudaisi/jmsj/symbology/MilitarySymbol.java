package com.github.yemikudaisi.jmsj.symbology;

public class MilitarySymbol
{
	private String version = "10";
	private StandardEntityOnes standardEntityOne = StandardEntityOnes.Reality;
	private StandardEntityTwos standardEntityTwo = StandardEntityTwos.Friend;
	private SymbolSets symbolSet = SymbolSets.LandUnits;
	private Status status = Status.Present;
	private HQTFDummy hqTFDummy = HQTFDummy.NotApplicable;
	private Amplifier amplifier = BrigadeBelowEchelonAmplifier.Company;
	String sidcSetB = "";
	
    public MilitarySymbol() { }
    public MilitarySymbol(String version, StandardEntityOnes standardEntityOne, StandardEntityTwos standardEntityTwo, SymbolSets symbolSet, Status status, HQTFDummy hQTFDummy, Amplifier amplifier)
    {
        this.setVersion(version);
        this.setStandardEntityOne(standardEntityOne);
        this.setStandardEntityTwo(standardEntityTwo);
        this.setSymbolSet(symbolSet);
        this.setStatus(status);
        this.setHqTFDummy(hQTFDummy);
        this.setAmplifier(amplifier);   
    }

    public String getSidcSetA() { 
    	return getVersion() +
            getStandardEntityOne().getSidcPart() +
            getStandardEntityTwo().getSidcPart() +
            getSymbolSet().getSidcPart() +
            getStatus().getSidcPart() +
            getHqTFDummy().getSidcPart() +
            getAmplifier().getSidcPart(); 
    	}

    @Override
    public String toString()
    {
        return getSidcSetA()+sidcSetB;
    }
    
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public StandardEntityOnes getStandardEntityOne() {
		return standardEntityOne;
	}
	
	public void setStandardEntityOne(StandardEntityOnes standardEntityOne) {
		this.standardEntityOne = standardEntityOne;
	}
	
	public StandardEntityTwos getStandardEntityTwo() {
		return standardEntityTwo;
	}
	
	void setStandardEntityTwo(StandardEntityTwos standardEntityTwo) {
		this.standardEntityTwo = standardEntityTwo;
	}
	
	public SymbolSets getSymbolSet() {
		return symbolSet;
	}
	
	public void setSymbolSet(SymbolSets symbolSet) {
		this.symbolSet = symbolSet;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public HQTFDummy getHqTFDummy() {
		return hqTFDummy;
	}
	
	public void setHqTFDummy(HQTFDummy hqTFDummy) {
		this.hqTFDummy = hqTFDummy;
	}
	
	public Amplifier getAmplifier() {
		return amplifier;
	}
	
	public void setAmplifier(Amplifier amplifier) {
		this.amplifier = amplifier;
	}	
}
