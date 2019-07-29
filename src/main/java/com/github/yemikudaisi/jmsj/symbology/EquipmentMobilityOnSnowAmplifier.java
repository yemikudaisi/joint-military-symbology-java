package com.github.yemikudaisi.jmsj.symbology;

public enum EquipmentMobilityOnSnowAmplifier implements Amplifier {
		OverSnow("Oversnow(prime mover)","1"),
	    Sled("2");
    private String description;
    private String sidcPart;
    
    EquipmentMobilityOnSnowAmplifier(String sidcPart){
    	this(null, sidcPart);
    }

    EquipmentMobilityOnSnowAmplifier(String value, String sidcPart) {
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

    public static EquipmentMobilityOnSnowAmplifier getEnum(String value) {
        for(EquipmentMobilityOnSnowAmplifier v : values())
            if(v.getSidcPart().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
