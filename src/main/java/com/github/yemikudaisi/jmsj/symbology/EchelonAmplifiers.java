package com.github.yemikudaisi.jmsj.symbology;

public enum EchelonAmplifiers implements Amplifier {
    Team("Team/Crew","11"),
    Squad("12"),
    Section("13"),
    Platoon("Platoon/Deatachment","14"),
    Company("Company/Battery/Troop","15"),
    Battalion("Battalion/Squadron","16"),
    Regiment("Regiment/Group","17"),
    Brigade("18"),
	Division("21"),
    Corp("22"),
    Army("23"),
    ArmyGroup("Army Group/Front","24"),
    Region("Region/Theater","25"),
    Command("26");
    
    private String description;
    private String sidcPart;
    
    EchelonAmplifiers (String sidcPart){
    	this(null, sidcPart);
    }

    EchelonAmplifiers (String value, String sidcPart) {
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

    public static EchelonAmplifiers  getEnum(String value) {
        for(EchelonAmplifiers  v : values())
            if(v.getSidcPart().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
