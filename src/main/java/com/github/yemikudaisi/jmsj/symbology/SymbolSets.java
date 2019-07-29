package com.github.yemikudaisi.jmsj.symbology;

public enum SymbolSets
{
    Air("01"),
    AirMissile ("Air Missile","02"),
    Space("05"),
    SpaceMissile("Space Missile","06"),
    LandUnits("Land Units","10"),
    LandCivilian ("Land Civilian","11"),
    LandEquipment ("Land Equipment","15"),
    LandInstallation("Land Installation","20"),
    ControlMeasure ("Control Measure","25"),
    SeaSurface("Sea Surface","30"),
    SeaSubsurface("Sea Subsurface","35"),
    MineWarfare ("Mine Warfare","36"),
    Activities("40"),
    MeteorologicalAtmospheric("Meteorological - Atmospheric","45"),
    MeteorologicalOceanographic ("Meteorological - Oceanpgraphic","46"),
    MeteorologicalSpace("Meteorological - Space","47"),
    SignalsIntelligenceSpace("Signals Intelligence -Space","50"),
    SignalsIntelligenceAir("Signals Intelligence - Air","51"),
    SignalsIntelligenceLand("Signals Intelligence - Land", "52"),
    SignalsIntelligenceSurface("Signals Intelligence - Surface","53"),
    SignalsIntelligenceSubsurface("Signals Intelligence - Subsurface","54"),
    Cyberspace("60");
    
    
    private String description;
    private String sidcPart;
    
    SymbolSets(String sidcPart){
    	this(null, sidcPart);
    }

    SymbolSets(String value, String sidcPart) {
        this.description = value;
        this.sidcPart = sidcPart;
    }

    public String getDescription() {
        return description;
    }
    
    public String getSidcPart() {
    	return sidcPart;
    }
    
    @Override
    public String toString() {
    	if (this.description!=null)
    		return this.getDescription();
    	return super.toString();
    }

    public static SymbolSets getEnum(String value) {
        for(SymbolSets v : values())
            if(v.getDescription().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
