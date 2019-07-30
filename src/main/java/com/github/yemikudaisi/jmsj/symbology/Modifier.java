package com.github.yemikudaisi.jmsj.symbology;

public class Modifier extends DomainCoded
{
    public Modifier(String name, String identifier){
    	super(name, identifier);
    }
    
    @Override
    public String toString() {
    	return "-->"+getName()+" - "+getIdentifier()+"\n";
    }
    
    @Override
    public void setIdentifier(String c) {
    	if (c.length() > 2) {
    		throw new IllegalArgumentException("Modifiers expect a single character as identifier");
    	}else if(c.length() == 1) {
    		c = "0"+c;
    	}
    	super.setIdentifier(c);
    }
}

