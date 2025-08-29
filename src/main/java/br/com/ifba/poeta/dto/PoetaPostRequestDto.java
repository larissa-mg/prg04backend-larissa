package br.com.ifba.poeta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/*
 * DTO para receber dados de criação ou atualização de um poeta.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoetaPostRequestDto {

    @JsonProperty(value = "nome")
    @NotBlank(message = "O nome é obrigatório!")
    private String nome;

    @JsonProperty(value = "dataNascimento")
    @NotNull(message = "A data de nascimento é obrigatória!")
    private LocalDate dataNascimento;

    @JsonProperty(value = "biografia")
    @NotBlank(message = "A biografia é obrigatória!")
    private String biografia;
}