package br.com.ifba.poeta.controller;

import br.com.ifba.infrastructure.mapper.ObjectMapperUtil;
import br.com.ifba.poeta.dto.PoetaGetResponseDto;
import br.com.ifba.poeta.dto.PoetaPostRequestDto;
import br.com.ifba.poeta.entity.Poeta;
import br.com.ifba.poeta.service.PoetaIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
// Define o controlador REST para Poeta.
@RestController
@RequestMapping(path = "/poetas")
@RequiredArgsConstructor
public class PoetaController {

    private final PoetaIService poetaService;
    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PoetaGetResponseDto> save(@RequestBody @Valid PoetaPostRequestDto poetaPostRequestDto) {
        Poeta poeta = objectMapperUtil.map(poetaPostRequestDto, Poeta.class);
        Poeta savedPoeta = poetaService.save(poeta);
        return ResponseEntity.status(HttpStatus.CREATED).body(objectMapperUtil.map(savedPoeta, PoetaGetResponseDto.class));
    }

    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PoetaGetResponseDto>> findAll() {
        List<Poeta> allPoetas = poetaService.findAll();
        List<PoetaGetResponseDto> dtoList = objectMapperUtil.mapAll(allPoetas, PoetaGetResponseDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PoetaGetResponseDto> update(@RequestBody @Valid PoetaPostRequestDto poetaPostRequestDto) {
        Poeta poeta = objectMapperUtil.map(poetaPostRequestDto, Poeta.class);
        Poeta updatedPoeta = poetaService.update(poeta);
        return ResponseEntity.status(HttpStatus.OK).body(objectMapperUtil.map(updatedPoeta, PoetaGetResponseDto.class));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        poetaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/findbyid/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PoetaGetResponseDto> findById(@PathVariable("id") Long id) {
        Poeta poeta = poetaService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(objectMapperUtil.map(poeta, PoetaGetResponseDto.class));
    }
}