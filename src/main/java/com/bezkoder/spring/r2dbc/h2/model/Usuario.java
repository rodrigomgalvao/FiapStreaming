package com.bezkoder.spring.r2dbc.h2.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

	@Id
	private int id;
	
	private String nomeUsuario;


	public Usuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
}
