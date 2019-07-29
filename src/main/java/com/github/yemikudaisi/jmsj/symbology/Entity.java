package com.github.yemikudaisi.jmsj.symbology;

import java.util.ArrayList;
import java.util.List;

public class Entity extends DomainCoded
{
    private List<EntityType> types;

    public Entity(String name, String identifier)
    {
    	super(name, identifier);
    	types = new ArrayList<EntityType>();
    }

	public List<EntityType> getTypes() {
		return types;
	}

	public void setTypes(List<EntityType> type) {
		this.types = type;
	}
	
	@Override
    public String toString() {
    	StringBuilder builder = new StringBuilder();
    	builder.append("--->"+getName()+"\n");
    	for(EntityType t: types) {
    		builder.append(t.toString());
    	}
    	return builder.toString();
    }
}
