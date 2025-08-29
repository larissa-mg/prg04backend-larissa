package br.com.ifba.categoria.controller;

import br.com.ifba.categoria.dto.CategoriaGetResponseDto;
import br.com.ifba.categoria.dto.CategoriaPostRequestDto;
import br.com.ifba.categoria.entity.Categoria;
import br.com.ifba.categoria.service.CategoriaIService;
import br.com.ifba.infrastructure.mapper.ObjectMapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
// Define o controlador REST para Categoria.
@RestController
@RequestMapping(path = "/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaIService categoriaService;
    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriaGetResponseDto> save(@RequestBody @Valid CategoriaPostRequestDto categoriaPostRequestDto) {
        Categoria categoria = objectMapperUtil.map(categoriaPostRequestDto, Categoria.class);
        Categoria savedCategoria = categoriaService.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(objectMapperUtil.map(savedCategoria, CategoriaGetResponseDto.class));
    }

    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoriaGetResponseDto>> findAll() {
        List<Categoria> allCategorias = categoriaService.findAll();
        List<CategoriaGetResponseDto> dtoList = objectMapperUtil.mapAll(allCategorias, CategoriaGetResponseDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriaGetResponseDto> update(@RequestBody @Valid CategoriaPostRequestDto categoriaPostRequestDto) {
        Categoria categoria = objectMapperUtil.map(categoriaPostRequestDto, Categoria.class);
        Categoria updatedCategoria = categoriaService.update(categoria);
        return ResponseEntity.status(HttpStatus.OK).body(objectMapperUtil.map(updatedCategoria, CategoriaGetResponseDto.class));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/findbyid/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriaGetResponseDto> findById(@PathVariable("id") Long id) {
        Categoria categoria = categoriaService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(objectMapperUtil.map(categoria, CategoriaGetResponseDto.class));
    }
}