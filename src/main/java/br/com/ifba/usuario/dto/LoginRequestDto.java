package br.com.ifba.usuario.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginRequestDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "senha")
    private String senha;
}