package com.github.yemikudaisi.jmsj.symbology;

public enum StandardEntityTwos
{
    Pending("0"),
    Unknown("1"),
    AssumedFriend("Assumed Friend","2"),
    Friend("3"),
    Neutral("4"),
    Suspect("Suspect/Joker","5"),
    Hostile("Hostile/Faker","6");  
    
    private String description;
    private String sidcPart;
    
    StandardEntityTwos(String sidcPart){
    	this(null, sidcPart);
    }

    StandardEntityTwos(String value, String sidcPart) {
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

    public static StandardEntityTwos getEnum(String value) {
        for(StandardEntityTwos v : values())
            if(v.getDescription().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
