package com.github.yemikudaisi.jmsj.symbology;

import java.util.ArrayList;
import java.util.List;

public class EntityModifierHeirarchy {
	private List<Entity> entities;
	private SymbolSets set;
	private List<Modifier> modifierOnes;
	private List<Modifier> modifierTwos;
	
	
	public EntityModifierHeirarchy(SymbolSets set) {
		entities = new ArrayList<Entity>();
		modifierOnes = new ArrayList<Modifier>();
		modifierTwos = new ArrayList<Modifier>();
		this.set = set;
	}

	public List<Modifier> getModifierOnes() {
		return modifierOnes;
	}

	public void setModifierOnes(List<Modifier> modifierOnes) {
		this.modifierOnes = modifierOnes;
	}

	public List<Modifier> getModifierTwos() {
		return modifierTwos;
	}

	public void setModifierTwos(List<Modifier> modifierTwos) {
		this.modifierTwos = modifierTwos;
	}

	public SymbolSets getSet() {
		return set;
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
    	builder.append("->"+set.getDescription()+"\n");
    	for(Entity e: entities) {
    		builder.append(e.toString());
    	}
    	return builder.toString();
    }
}
