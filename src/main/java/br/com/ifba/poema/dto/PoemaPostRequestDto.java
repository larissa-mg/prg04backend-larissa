package br.com.ifba.poema.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
 * DTO para receber dados de criação ou atualização de um poema.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoemaPostRequestDto {

    @JsonProperty(value = "titulo")
    @NotBlank(message = "O título é obrigatório!")
    private String titulo;

    @JsonProperty(value = "conteudo")
    @NotBlank(message = "O conteúdo é obrigatório!")
    private String conteudo;

    @NotNull(message = "O ano de publicação é obrigatório.")
    private Integer dataPublicacao;

    @JsonProperty(value = "poetaId")
    @NotNull(message = "O ID do poeta é obrigatório!")
    private Long poetaId;

    @JsonProperty(value = "usuarioId")
    @NotNull(message = "O ID do usuário que publicou o poema é obrigatório!")
    private Long usuarioId;

    @JsonProperty(value = "categoriaIds")
    @NotNull(message = "A lista de categorias é obrigatória!")
    private List<Long> categoriaIds;
}