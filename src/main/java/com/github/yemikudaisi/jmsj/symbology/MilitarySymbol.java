package com.github.yemikudaisi.jmsj.symbology;

public class MilitarySymbol
{
	private String version = "10";
	private StandardEntityOnes standardEntityOne = StandardEntityOnes.Reality;
	private StandardEntityTwos standardEntityTwo = StandardEntityTwos.Friend;
	private SymbolSets symbolSet = SymbolSets.LandUnits;
	private Status status = Status.Present;
	private HQTFDummy hqTFDummy = HQTFDummy.NotApplicable;
	private Amplifier amplifier = NotApplicableAmplifier.Unspecified;
	
	private Entity entity;
	private EntityType entityType;
	private EntitySubType entitySubType;
	private Modifier sectorOneModifier = new Modifier("00","00");
	private Modifier sectorTwoModifier = new Modifier("00","00");
	private StatusAmplifierModes statusAmplifierMode = StatusAmplifierModes.Default;
	
    public StatusAmplifierModes getStatusAmplifierMode() {
		return statusAmplifierMode;
	}

	public void setStatusAmplifierMode(StatusAmplifierModes statusAmplifierMode) {
		this.statusAmplifierMode = statusAmplifierMode;
	}

	public Modifier getSectorOneModifier() {
		return sectorOneModifier;
	}

	public void setSectorOneModifier(Modifier sectorOneModifer) {
		this.sectorOneModifier = sectorOneModifer;
	}

	public Modifier getSectorTwoModifier() {
		return sectorTwoModifier;
	}

	public void setSectorTwoModifier(Modifier sectorTwoModifer) {
		this.sectorTwoModifier = sectorTwoModifer;
	}

	public MilitarySymbol() {

        this.entity = new Entity("Unspecified","120000");
        this.entityType = new EntityType("Unspecified","120500");
        this.entitySubType = new EntitySubType("Unspecified","120501");
	}
    
    public MilitarySymbol(String version, StandardEntityOnes standardEntityOne, StandardEntityTwos standardEntityTwo, SymbolSets symbolSet, Status status, HQTFDummy hQTFDummy, Amplifier amplifier)
    {
        this.setVersion(version);
        this.setStandardEntityOne(standardEntityOne);
        this.setStandardEntityTwo(standardEntityTwo);
        this.setSymbolSet(symbolSet);
        this.setStatusAmplifier(status);
        this.setHqTFDummy(hQTFDummy);
        this.setAmplifier(amplifier); 
    }
    
    public MilitarySymbol(
    		String version, 
    		StandardEntityOnes standardEntityOne, 
    		StandardEntityTwos standardEntityTwo, 
    		SymbolSets symbolSet, 
    		Status status, 
    		HQTFDummy hQTFDummy, 
    		Amplifier amplifier,
    		Entity entity,
    		EntityType entityType,
    		EntitySubType entitySubType)
    {
        this.setVersion(version);
        this.setStandardEntityOne(standardEntityOne);
        this.setStandardEntityTwo(standardEntityTwo);
        this.setSymbolSet(symbolSet);
        this.setStatusAmplifier(status);
        this.setHqTFDummy(hQTFDummy);
        this.setAmplifier(amplifier); 
        this.setEntity(entity);
        this.setEntityType(entityType);
        this.setEntitySubType(entitySubType);
    }

    public String getSidcSetA() { 
    	return getVersion() +
            getStandardEntityOne().getSidcPart() +
            getStandardEntityTwo().getSidcPart() +
            getSymbolSet().getSidcPart() +
            getStatusAmplifier().getSidcPart() +
            getHqTFDummy().getSidcPart() +
            getAmplifier().getSidcPart(); 
	}

    public String getSidcSetB() {
    	String setB = getEntity().getIdentifier().substring(0,2)+
    			getEntityType().getIdentifier().substring(2,4)+
    			getEntitySubType().getIdentifier().substring(4,6)+
    			getSectorOneModifier().getIdentifier()+
    			getSectorTwoModifier().getIdentifier();
    	
    	return setB;
    }
    
    public EntityType getEntityType() {
		return entityType;
	}
    
	public void setEntityType(EntityType entityType){
		this.entityType = entityType;
	}
	
	public EntitySubType getEntitySubType() {
		return entitySubType;
	}
	
	public void setEntitySubType(EntitySubType entitySubType) {
		this.entitySubType = entitySubType;
	}
	
	@Override
    public String toString()
    {
        return getSidcSetA()+getSidcSetB();
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
	
	public void setStandardEntityTwo(StandardEntityTwos standardEntityTwo) {
		this.standardEntityTwo = standardEntityTwo;
	}
	
	public SymbolSets getSymbolSet() {
		return symbolSet;
	}
	
	public void setSymbolSet(SymbolSets symbolSet) {
		this.symbolSet = symbolSet;
	}
	
	// TODO: Check is the supplied amplifier is applicable to the symbol set
	// and log details
	public Status getStatusAmplifier() {
		return status;
	}
	
	public void setStatusAmplifier(Status status) {
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
	private Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}	
}
