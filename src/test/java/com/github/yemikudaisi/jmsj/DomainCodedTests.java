package com.github.yemikudaisi.jmsj;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.yemikudaisi.jmsj.symbology.DomainCoded;
import com.github.yemikudaisi.jmsj.symbology.Entity;

public class DomainCodedTests {

	@Test
	public void equialityTest() {
		DomainCoded a = new Entity("x","00");
		DomainCoded b = new Entity("x","00");
		assertTrue(a.equals(b));
	}
	
	@Test
	public void hashTest() {
		EqualsTester<DomainCoded> equalsTester = EqualsTester.newInstance( new Entity("x","000000") );
	    equalsTester.assertEqual( new Entity("x","000000"), new Entity("x","000000") );
	    equalsTester.assertNotEqual( new Entity("x","000000"), new Entity("x","000001") );
	}

}
