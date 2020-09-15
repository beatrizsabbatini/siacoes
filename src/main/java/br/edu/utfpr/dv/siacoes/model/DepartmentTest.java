package br.edu.utfpr.dv.siacoes.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class DepartmentTest {
	
	private final Department d = new Department();

	@Test
	public void T01() {
		d.setInitials("DACOM");
	}
	
	@Test
	public void T02() {
		assertEquals("DACOM", d.getInitials());
	}

}
