package com.github.yemikudaisi.jmsj.symbology;

public enum BrigadeBelowEchelonAmplifier implements Amplifier {
    Team("Team/Crew","11"),
    Squad("12"),
    Section("13"),
    Platoon("Platoon/Deatachment","14"),
    Company("Company/Battery/Troop","15"),
    Battalion("Battalion/Squadron","16"),
    Regiment("Regiment/Group","17"),
    Brigade("18");
    
    private String description;
    private String sidcPart;
    
    BrigadeBelowEchelonAmplifier (String sidcPart){
    	this(null, sidcPart);
    }

    BrigadeBelowEchelonAmplifier (String value, String sidcPart) {
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

    public static BrigadeBelowEchelonAmplifier  getEnum(String value) {
        for(BrigadeBelowEchelonAmplifier  v : values())
            if(v.getDescription().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
