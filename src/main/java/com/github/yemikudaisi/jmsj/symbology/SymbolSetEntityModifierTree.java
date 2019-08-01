package com.github.yemikudaisi.jmsj.symbology;

import java.util.ArrayList;
import java.util.List;

public class SymbolSetEntityModifierTree {
	private List<Entity> entities;
	private SymbolSets set;
	private List<Modifier> sectorModifierOnes;
	private List<Modifier> sectorModifierTwos;
	
	
	public SymbolSetEntityModifierTree(SymbolSets set) {
		entities = new ArrayList<Entity>();
		sectorModifierOnes = new ArrayList<Modifier>();
		sectorModifierTwos = new ArrayList<Modifier>();
		this.set = set;
	}

	public List<Modifier> getSectorModifierOnes() {
		return sectorModifierOnes;
	}

	public void setSectorModifierOnes(List<Modifier> modifierOnes) {
		this.sectorModifierOnes = modifierOnes;
	}

	public List<Modifier> getSectorModifierTwos() {
		return sectorModifierTwos;
	}

	public void setSectorModifierTwos(List<Modifier> modifierTwos) {
		this.sectorModifierTwos = modifierTwos;
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
    	builder.append("# Entities -> Types -> Subtypes: \n");
    	for(Entity e: entities) {
    		builder.append(e.toString());
    	}
    	
    	builder.append("\n# Modifier One: \n");
    	for (Modifier e: sectorModifierOnes) {
    		builder.append(e.toString());
    	}
    	
    	builder.append("# Modifier Two: \n");
    	for (Modifier e: sectorModifierTwos) {
    		builder.append(e.toString());
    	}
    	return builder.toString();
    }
}
