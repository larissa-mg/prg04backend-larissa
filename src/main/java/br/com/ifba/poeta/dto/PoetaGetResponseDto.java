package br.com.ifba.poeta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/*
 * DTO para retornar dados do poeta na resposta da API.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoetaGetResponseDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "nome")
    private String nome;

    @JsonProperty(value = "dataNascimento")
    private LocalDate dataNascimento;

    @JsonProperty(value = "biografia")
    private String biografia;
}