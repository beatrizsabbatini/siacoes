﻿package br.edu.utfpr.dv.siacoes.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Campus implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int idCampus;
	private String name;
	private String address;
	private transient byte[] logo;
	private boolean active;
	private String site;
	private String initials;


}
