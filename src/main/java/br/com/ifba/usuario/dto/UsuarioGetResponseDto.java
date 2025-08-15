package br.com.ifba.usuario.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * DTO para retornar dados do usuário na resposta da API.
 * Não inclui a senha para não expor informações sensíveis.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioGetResponseDto {

    // O nome do usuário
    @JsonProperty(value = "nome")
    private String nome;

    // O e-mail do usuário
    @JsonProperty(value = "email")
    private String email;
}
