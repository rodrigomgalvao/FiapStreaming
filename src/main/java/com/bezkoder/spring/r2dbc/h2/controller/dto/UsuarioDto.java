package com.bezkoder.spring.r2dbc.h2.controller.dto;

import com.bezkoder.spring.r2dbc.h2.model.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class UsuarioDto {
    @JsonProperty
    @NotBlank(message = "Campo nome do usuário não pode ser branco ou nulo.")
    private String nomeUsuario;

    public Usuario toUsuario() {
        return new Usuario(nomeUsuario);
    }
}
