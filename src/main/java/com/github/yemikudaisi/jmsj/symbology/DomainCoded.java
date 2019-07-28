package com.github.yemikudaisi.jmsj.symbology;

public class DomainCoded implements Comparable<DomainCoded>{
    private String name;
    private String identifier;

    public DomainCoded(String name, String identifier){
        this.setName(name);
        this.setIdentifier(identifier);
    }

    public int compareTo(DomainCoded obj){
        return (this.identifier.equalsIgnoreCase(obj.getIdentifier())?1:0);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
}