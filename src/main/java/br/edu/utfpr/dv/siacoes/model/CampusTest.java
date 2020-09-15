package br.edu.utfpr.dv.siacoes.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CampusTest {
	
	private final Campus c = new Campus();

	@Test
	public void T01() {
		c.setAddress("Cornélio Procópio");
		
	}
	
	@Test
	public void T02() {
		assertEquals("Cornélio Procópio", c.getAddress());
	}

}
