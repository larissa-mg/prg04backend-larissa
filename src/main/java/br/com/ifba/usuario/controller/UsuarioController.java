package br.com.ifba.usuario.controller;

import br.com.ifba.usuario.entity.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// Define controlador REST que retorna dados no formato JSON/XML
@RestController
// Mapeia todas as URLs deste controller para começar com '/usuarios'
@RequestMapping(path = "/usuarios")
public class UsuarioController {

    // Lista para armazenar usuários
    private final List<Usuario> usuarios = new ArrayList<>();

    // Construtor que inicializa a lista com 3 usuários
    public UsuarioController() {
        usuarios.add(new Usuario("João Silva", "joao@email.com", "123"));
        usuarios.add(new Usuario("Maria Souza", "maria@email.com", "123"));
        usuarios.add(new Usuario("Carlos Oliveira", "carlos@email.com", "123"));
    }

    // Endpoint GET para retornar todos os usuários
    // URL: GET http://localhost:8080/usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        // Retorna a lista de usuários com status HTTP 200 (OK)
        return ResponseEntity.ok(usuarios);
    }

    // Endpoint POST para criar um novo usuário
    // URL: POST http://localhost:8080/usuarios
    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        // Gera um ID simples baseado no tamanho atual da lista
        usuario.setId((long) (usuarios.size() + 1));
        usuarios.add(usuario);

        // Retorna o usuário criado com status HTTP 201 (CREATED)
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }
}
