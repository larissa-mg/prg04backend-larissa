package br.com.ifba.usuario.controller;

import br.com.ifba.infrastructure.mapper.ObjectMapperUtil;
import br.com.ifba.usuario.dto.UsuarioGetResponseDto;
import br.com.ifba.usuario.dto.UsuarioPostRequestDto;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.service.UsuarioIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Define controlador REST que retorna dados no formato JSON/XML
@RestController
// Mapeia todas as URLs deste controller para começar com '/usuarios'
@RequestMapping(path = "/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioIService usuarioService;
    private final ObjectMapperUtil objectMapperUtil;

    // Endpoint POST para salvar um novo usuário.
    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody UsuarioPostRequestDto usuarioPostRequestDto) {
        // Converte o DTO para a entidade, atualiza, e converte o resultado de volta para um DTO de resposta.
        return ResponseEntity.status(HttpStatus.CREATED).body(objectMapperUtil.map(
                usuarioService.save((objectMapperUtil.map(
                        usuarioPostRequestDto,
                        Usuario.class))),
                UsuarioGetResponseDto.class));
    }

    // Endpoint GET para retornar todos os usuários.
    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(objectMapperUtil.mapAll(
                this.usuarioService.findAll(),
                UsuarioGetResponseDto.class
        ));
    }

    // Endpoint PUT para atualizar um usuário existente.
    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody UsuarioPostRequestDto usuarioPostRequestDto) {
        // Converte o DTO para a entidade, atualiza, e converte o resultado de volta para um DTO de resposta.
        return ResponseEntity.status(HttpStatus.OK).body(objectMapperUtil.map(
                usuarioService.update((objectMapperUtil.map(
                        usuarioPostRequestDto,
                        Usuario.class))),
                UsuarioGetResponseDto.class));
    }

    // Endpoint DELETE para remover um usuário.
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint GET para buscar um único usuário por ID.
    @GetMapping(path = "/findbyid/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        // Busca o usuário no serviço e mapeia o resultado para um DTO antes de retornar.
        return ResponseEntity.status(HttpStatus.OK).body(objectMapperUtil.map(
                usuarioService.findById(id),
                UsuarioGetResponseDto.class));
    }
}