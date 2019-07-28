package com.github.yemikudaisi.jmsj.symbology;

public enum EquipmentMobilityOnLandAmplifier implements Amplifier  {
	
	WheeledLimitedCrossCountry("Wheeled limited cross country","31"),
	WheeledCrossCountry("Wheeled cross country","32"),
	Tracked("33"),
	WheeledAndTrackedCombination("Wheeled and tracked combination","34"),
	Towed("35"),
	Rail("36"),
	PackAnimals("Pack animals","37");
	
    private String description;
    private String sidcPart;
    
    EquipmentMobilityOnLandAmplifier(String sidcPart){
    	this(null, sidcPart);
    }

    EquipmentMobilityOnLandAmplifier(String value, String sidcPart) {
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

    public static EquipmentMobilityOnLandAmplifier getEnum(String value) {
        for(EquipmentMobilityOnLandAmplifier v : values())
            if(v.getDescription().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
