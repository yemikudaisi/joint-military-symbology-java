package com.github.yemikudaisi.jmsj.symbology;

import java.util.List;

public class SymbolSet extends DomainCoded
{
	private List<Entity> entities;
	
    public SymbolSet(String name, String identifier, List<Entity> entities)
    {
    	super(name, identifier);
        this.entities = entities;
    }

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

}
