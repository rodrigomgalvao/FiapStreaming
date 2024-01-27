package com.code4.fiapstreaming.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.code4.fiapstreaming.model.Usuario;

public interface UsuarioRepository extends R2dbcRepository<Usuario, Integer>{

}
