package com.github.yemikudaisi.jmsj.symbology;

public enum EquipmentMobilityAmplifiers implements Amplifier  {
	
	WheeledLimitedCrossCountry("Wheeled limited cross country","31"),
	WheeledCrossCountry("Wheeled cross country","32"),
	Tracked("33"),
	WheeledAndTrackedCombination("Wheeled and tracked combination","34"),
	Towed("35"),
	Rail("36"),
	PackAnimals("Pack animals","37"),
	OverSnow("Oversnow(prime mover)","41"),
    Sled("42"),
    Barge("1"),
	Amphibious("52");
	
    private String description;
    private String sidcPart;
    
    EquipmentMobilityAmplifiers(String sidcPart){
    	this(null, sidcPart);
    }

    EquipmentMobilityAmplifiers(String value, String sidcPart) {
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

    public static EquipmentMobilityAmplifiers getEnum(String value) {
        for(EquipmentMobilityAmplifiers v : values())
            if(v.getSidcPart().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
