package com.github.yemikudaisi.jmsj.symbology;

public enum HQTFDummy
{
    NotApplicable("Not Applicable","0"),
    FientDummy("Fient/Dummy","1"),
    Headquarters("2"),
    FientDummyHeadquarters("Fient/ Dummy Headquarters","3"),
    TaskForce("Task Force","4"),
    FientDummyTaskforce("Fient/Dummy Task Force","5"),
    TaskForceHeadquarters("Task Force Headquarters","6"),
    FientDummyTaskforceHeadquarters("Fient/ Dummy Task Force Headquarters","7");
	
	private String description;
    private String sidcPart;
    
    HQTFDummy(String sidcPart){
    	this(null, sidcPart);
    }

    HQTFDummy(String value, String sidcPart) {
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

    public static HQTFDummy getEnum(String value) {
        for(HQTFDummy v : values())
            if(v.getSidcPart().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
