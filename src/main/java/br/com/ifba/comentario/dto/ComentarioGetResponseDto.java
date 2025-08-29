package br.com.ifba.comentario.dto;

import br.com.ifba.poema.dto.PoemaGetResponseDto;
import br.com.ifba.usuario.dto.UsuarioGetResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/*
 * DTO para retornar dados do comentário na resposta da API.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioGetResponseDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "conteudo")
    private String conteudo;

    @JsonProperty(value = "dataPublicacao")
    private LocalDate dataPublicacao;

    @JsonProperty(value = "poema")
    private PoemaGetResponseDto poema;

    @JsonProperty(value = "usuario")
    private UsuarioGetResponseDto usuario;
}