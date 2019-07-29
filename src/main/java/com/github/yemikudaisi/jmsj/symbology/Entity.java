package com.github.yemikudaisi.jmsj.symbology;

public class Entity extends DomainCoded
{
    private EntityType type;
    private EntitySubType subType;

    public Entity(String name, String identifier)
    {
    	super(name, identifier);
    }

	public EntityType getType() {
		return type;
	}

	public void setType(EntityType type) {
		this.type = type;
	}

	public EntitySubType getSubType() {
		return subType;
	}

	public void setSubType(EntitySubType subType) {
		this.subType = subType;
	}
}
