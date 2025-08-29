package br.com.ifba.comentario.controller;

import br.com.ifba.comentario.dto.ComentarioGetResponseDto;
import br.com.ifba.comentario.dto.ComentarioPostRequestDto;
import br.com.ifba.comentario.entity.Comentario;
import br.com.ifba.comentario.service.ComentarioIService;
import br.com.ifba.infrastructure.mapper.ObjectMapperUtil;
import br.com.ifba.poema.entity.Poema;
import br.com.ifba.usuario.entity.Usuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
// Define o controlador REST para Comentário.
@RestController
@RequestMapping(path = "/comentarios")
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioIService comentarioService;
    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComentarioGetResponseDto> save(@RequestBody @Valid ComentarioPostRequestDto comentarioPostRequestDto) {
        Comentario comentario = objectMapperUtil.map(comentarioPostRequestDto, Comentario.class);

        Poema poema = new Poema();
        poema.setId(comentarioPostRequestDto.getPoemaId());
        comentario.setPoema(poema);

        Usuario usuario = new Usuario();
        usuario.setId(comentarioPostRequestDto.getUsuarioId());
        comentario.setUsuario(usuario);

        Comentario savedComentario = comentarioService.save(comentario);
        return ResponseEntity.status(HttpStatus.CREATED).body(objectMapperUtil.map(savedComentario, ComentarioGetResponseDto.class));
    }

    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComentarioGetResponseDto>> findAll() {
        List<Comentario> allComentarios = comentarioService.findAll();
        List<ComentarioGetResponseDto> dtoList = objectMapperUtil.mapAll(allComentarios, ComentarioGetResponseDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComentarioGetResponseDto> update(@RequestBody @Valid ComentarioPostRequestDto comentarioPostRequestDto) {
        Comentario comentario = objectMapperUtil.map(comentarioPostRequestDto, Comentario.class);
        Comentario updatedComentario = comentarioService.update(comentario);
        return ResponseEntity.status(HttpStatus.OK).body(objectMapperUtil.map(updatedComentario, ComentarioGetResponseDto.class));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        comentarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/findbyid/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComentarioGetResponseDto> findById(@PathVariable("id") Long id) {
        Comentario comentario = comentarioService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(objectMapperUtil.map(comentario, ComentarioGetResponseDto.class));
    }
}