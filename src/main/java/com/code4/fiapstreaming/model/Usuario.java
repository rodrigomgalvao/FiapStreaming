package com.code4.fiapstreaming.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

	@Id
	private UUID id;
	private String nomeUsuario;

}
