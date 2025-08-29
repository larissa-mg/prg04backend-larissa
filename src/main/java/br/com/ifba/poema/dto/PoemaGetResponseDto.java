package br.com.ifba.poema.dto;

import br.com.ifba.categoria.dto.CategoriaGetResponseDto;
import br.com.ifba.comentario.dto.ComentarioGetResponseDto;
import br.com.ifba.poeta.dto.PoetaGetResponseDto;
import br.com.ifba.usuario.dto.UsuarioGetResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
 * DTO para retornar dados do poema na resposta da API.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoemaGetResponseDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "titulo")
    private String titulo;

    @JsonProperty(value = "conteudo")
    private String conteudo;

    @JsonProperty(value = "dataPublicacao")
    private Integer dataPublicacao;

    @JsonProperty(value = "poeta")
    private PoetaGetResponseDto poeta;

    @JsonProperty(value = "usuario")
    private UsuarioGetResponseDto usuario;

    @JsonProperty(value = "categorias")
    private List<CategoriaGetResponseDto> categorias;

    @JsonProperty(value = "comentarios")
    private List<ComentarioGetResponseDto> comentarios;
}