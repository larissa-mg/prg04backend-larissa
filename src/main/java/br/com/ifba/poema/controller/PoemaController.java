package br.com.ifba.poema.controller;

import br.com.ifba.categoria.entity.Categoria;
import br.com.ifba.infrastructure.mapper.ObjectMapperUtil;
import br.com.ifba.poema.dto.PoemaGetResponseDto;
import br.com.ifba.poema.dto.PoemaPostRequestDto;
import br.com.ifba.poema.entity.Poema;
import br.com.ifba.poema.service.PoemaIService;
import br.com.ifba.poeta.entity.Poeta;
import br.com.ifba.usuario.entity.Usuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
// Define o controlador REST para Poema.
@RestController
@RequestMapping(path = "/poemas")
@RequiredArgsConstructor
public class PoemaController {

    private final PoemaIService poemaService;
    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PoemaGetResponseDto> save(@RequestBody @Valid PoemaPostRequestDto poemaPostRequestDto) {
        Poema poema = objectMapperUtil.map(poemaPostRequestDto, Poema.class);

        Poeta poeta = new Poeta();
        poeta.setId(poemaPostRequestDto.getPoetaId());
        poema.setPoeta(poeta);

        Usuario usuario = new Usuario();
        usuario.setId(poemaPostRequestDto.getUsuarioId());
        poema.setUsuario(usuario);

        List<Categoria> categorias = poemaPostRequestDto.getCategoriaIds().stream()
                .map(categoriaId -> {
                    Categoria categoria = new Categoria();
                    categoria.setId(categoriaId);
                    return categoria;
                })
                .toList();
        poema.setCategorias(categorias);

        Poema savedPoema = poemaService.save(poema);
        return ResponseEntity.status(HttpStatus.CREATED).body(objectMapperUtil.map(savedPoema, PoemaGetResponseDto.class));
    }

    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PoemaGetResponseDto>> findAll() {
        List<Poema> allPoemas = poemaService.findAll();
        List<PoemaGetResponseDto> dtoList = objectMapperUtil.mapAll(allPoemas, PoemaGetResponseDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PoemaGetResponseDto> update(@RequestBody @Valid PoemaPostRequestDto poemaPostRequestDto) {
        Poema poema = objectMapperUtil.map(poemaPostRequestDto, Poema.class);
        Poema updatedPoema = poemaService.update(poema);
        return ResponseEntity.status(HttpStatus.OK).body(objectMapperUtil.map(updatedPoema, PoemaGetResponseDto.class));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        poemaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/findbyid/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PoemaGetResponseDto> findById(@PathVariable("id") Long id) {
        Poema poema = poemaService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(objectMapperUtil.map(poema, PoemaGetResponseDto.class));
    }

    @GetMapping("/findbypoeta/{poetaId}")
    public ResponseEntity<List<PoemaGetResponseDto>> findByPoetaId(@PathVariable("poetaId") Long poetaId) {
        List<Poema> poemas = poemaService.findByPoetaId(poetaId);
        List<PoemaGetResponseDto> dtos = poemas.stream()
                .map(poema -> objectMapperUtil.map(poema, PoemaGetResponseDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}