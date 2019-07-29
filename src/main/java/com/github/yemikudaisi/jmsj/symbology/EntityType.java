package com.github.yemikudaisi.jmsj.symbology;

import java.util.ArrayList;
import java.util.List;

public class EntityType extends DomainCoded
{
    private List<EntitySubType> subTypes;
    
    public EntityType(String name, String identifier){
    	super(name, identifier);
    	subTypes = new ArrayList<EntitySubType>();
    }    

	public List<EntitySubType> getSubTypes() {
		return subTypes;
	}

	public void setSubTypes(List<EntitySubType> subTypes) {
		this.subTypes = subTypes;
	}
	
	@Override
    public String toString() {
    	StringBuilder builder = new StringBuilder();
    	builder.append("----->"+getName()+"\n");
    	for(EntitySubType t: subTypes) {
    		builder.append(t.toString());
    	}
    	return builder.toString();
    }
}