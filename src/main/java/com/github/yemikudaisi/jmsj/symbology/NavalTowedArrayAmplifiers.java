package com.github.yemikudaisi.jmsj.symbology;

public enum NavalTowedArrayAmplifiers implements Amplifier  {
    Short("Short towed array","61"),
    Long("Long towed array","62");
    
    private String description;
    private String sidcPart;
    
    NavalTowedArrayAmplifiers (String sidcPart){
    	this(null, sidcPart);
    }

    NavalTowedArrayAmplifiers (String value, String sidcPart) {
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

    public static NavalTowedArrayAmplifiers  getEnum(String value) {
        for(NavalTowedArrayAmplifiers  v : values())
            if(v.getSidcPart().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }

}
