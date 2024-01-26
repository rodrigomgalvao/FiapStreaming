package com.bezkoder.spring.r2dbc.h2.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.bezkoder.spring.r2dbc.h2.model.Usuario;

public interface UsuarioRepository extends R2dbcRepository<Usuario, Integer>{

}
