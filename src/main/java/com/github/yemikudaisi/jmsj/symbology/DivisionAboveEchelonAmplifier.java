package com.github.yemikudaisi.jmsj.symbology;

public enum DivisionAboveEchelonAmplifier implements Amplifier {
    Division("21"),
    Corp("22"),
    Army("23"),
    ArmyGroup("Army Group/Front","24"),
    Region("Region/Theater","25"),
    Command("26");
    
    private String description;
    private String sidcPart;
    
    DivisionAboveEchelonAmplifier(String sidcPart){
    	this(null, sidcPart);
    }

    DivisionAboveEchelonAmplifier(String value, String sidcPart) {
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

    public static DivisionAboveEchelonAmplifier getEnum(String value) {
        for(DivisionAboveEchelonAmplifier v : values())
            if(v.getSidcPart().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
