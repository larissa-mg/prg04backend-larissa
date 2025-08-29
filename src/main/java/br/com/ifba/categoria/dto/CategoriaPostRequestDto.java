package br.com.ifba.categoria.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * DTO para receber dados de criação ou atualização de uma categoria.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaPostRequestDto {

    @JsonProperty(value = "nome")
    @NotBlank(message = "O nome é obrigatório!")
    private String nome;

    @JsonProperty(value = "descricao")
    private String descricao;
}