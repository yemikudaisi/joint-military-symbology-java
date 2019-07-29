package com.github.yemikudaisi.jmsj;

import com.github.yemikudaisi.jmsj.symbology.MilitarySymbol;

import static org.junit.Assert.*;

import org.junit.Test;

public class SymbolTest{
	
	
	@Test
	public void sidcSetATest()
    {
		MilitarySymbol milSym = new MilitarySymbol();
        assertEquals(milSym.getSidcSetA(), "1003100015");
    }

}
