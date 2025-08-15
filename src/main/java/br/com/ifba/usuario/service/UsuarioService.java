package br.com.ifba.usuario.service;

import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Classe de Serviço que gerencia a lógica de negócio para a entidade Usuario.
 * Ela usa o UsuarioRepository para acessar os dados no banco de dados.
 */
@Service
@RequiredArgsConstructor
public class UsuarioService implements UsuarioIService {

    private final UsuarioRepository usuarioRepository;

    // Salva um novo usuário no banco de dados.
    public Usuario save(Usuario usuario) {
        if (usuario.getId() != null && usuarioRepository.existsById(usuario.getId())) {
            throw new BusinessException("Usuário já existente.");
        }
        return usuarioRepository.save(usuario);
    }

    // Retorna uma lista com todos os usuários do banco de dados.
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    // Edita um usuário já existente no banco de dados.
    @Override
    public Usuario update(Usuario usuario) {
        if (!usuarioRepository.existsById(usuario.getId())) {
            throw new BusinessException("Usuário não encontrado.");
        }
        return usuarioRepository.save(usuario);
    }

    // Deleta um usuário no banco de dados.
    public void delete(Long id) {
        if(!usuarioRepository.existsById(id)) {
            throw new BusinessException("Usuário não encontrado.");
        }
        usuarioRepository.delete(this.findById(id));
    }

    // Busca um usuário por ID.
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new BusinessException("Usuário não encontrado.")
        );
    }
}