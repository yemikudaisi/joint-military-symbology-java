package com.github.yemikudaisi.jmsj.symbology;

import java.util.ArrayList;
import java.util.List;

public class EntityModifierHeirarchy {
	private List<Entity> entities;
	private SymbolSets set;
	
	public EntityModifierHeirarchy(SymbolSets set) {
		entities = new ArrayList<Entity>();
		this.set = set;
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}
	
	@Override
    public String toString() {
    	StringBuilder builder = new StringBuilder();
    	builder.append("->"+set.getDescription());
    	for(Entity e: entities) {
    		builder.append(e.toString());
    	}
    	return builder.toString();
    }
}
