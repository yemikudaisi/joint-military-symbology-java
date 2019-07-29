package com.github.yemikudaisi.jmsj;

import com.github.yemikudaisi.jmsj.symbology.MilitarySymbol;

import junit.framework.TestCase;

public class SymbolTest extends TestCase{
	
	public SymbolTest(String testName) {
		super(testName);
	}
	
	public void testSymbolSidcSetA()
    {
		MilitarySymbol milSym = new MilitarySymbol();
        assertEquals(milSym.getSidcSetA(), "1003100015");
    }

}
