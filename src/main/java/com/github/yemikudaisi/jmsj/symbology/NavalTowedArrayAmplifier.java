package com.github.yemikudaisi.jmsj.symbology;

public enum NavalTowedArrayAmplifier implements Amplifier  {
    ShortTowedArray("Short towed array","1"),
    LongTowedArray("Long towed array","2");
    
    private String description;
    private String sidcPart;
    
    NavalTowedArrayAmplifier (String sidcPart){
    	this(null, sidcPart);
    }

    NavalTowedArrayAmplifier (String value, String sidcPart) {
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

    public static NavalTowedArrayAmplifier  getEnum(String value) {
        for(NavalTowedArrayAmplifier  v : values())
            if(v.getSidcPart().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
