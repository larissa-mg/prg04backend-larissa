package br.com.ifba.categoria.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * DTO para retornar dados da categoria na resposta da API.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaGetResponseDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "nome")
    private String nome;

    @JsonProperty(value = "descricao")
    private String descricao;
}