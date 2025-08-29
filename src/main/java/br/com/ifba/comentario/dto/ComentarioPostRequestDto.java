package br.com.ifba.comentario.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * DTO para receber dados de criação ou atualização de um comentário.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioPostRequestDto {

    @JsonProperty(value = "conteudo")
    @NotBlank(message = "O conteúdo do comentário não pode ser vazio!")
    private String conteudo;

    @JsonProperty(value = "poemaId")
    @NotNull(message = "O ID do poema é obrigatório!")
    private Long poemaId;

    @JsonProperty(value = "usuarioId")
    @NotNull(message = "O ID do usuário é obrigatório!")
    private Long usuarioId;
}