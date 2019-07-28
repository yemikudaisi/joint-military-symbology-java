package com.github.yemikudaisi.jmsj.symbology;

public enum EquipmentMobilityOnWaterAmplifier implements Amplifier  {
    Barge("1"),
	Amphibious("2");
    private String description;
    private String sidcPart;
    
    EquipmentMobilityOnWaterAmplifier(String sidcPart){
    	this(null, sidcPart);
    }

    EquipmentMobilityOnWaterAmplifier(String value, String sidcPart) {
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

    public static EquipmentMobilityOnWaterAmplifier getEnum(String value) {
        for(EquipmentMobilityOnWaterAmplifier v : values())
            if(v.getDescription().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
