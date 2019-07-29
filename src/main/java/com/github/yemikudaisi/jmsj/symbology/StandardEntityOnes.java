package com.github.yemikudaisi.jmsj.symbology;

public enum StandardEntityOnes
{
    Reality("0"),
    Exercise("1"),
    Simulation("2");
	

    private String description;
    private String sidcPart;
    
    StandardEntityOnes(String sidcPart){
    	this(null, sidcPart);
    }

    StandardEntityOnes(String value, String sidcPart) {
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

    public static StandardEntityOnes getEnum(String value) {
        for(StandardEntityOnes v : values())
            if(v.getDescription().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}