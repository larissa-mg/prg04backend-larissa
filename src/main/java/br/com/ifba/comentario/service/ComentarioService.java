package br.com.ifba.comentario.service;

import br.com.ifba.comentario.entity.Comentario;
import br.com.ifba.comentario.repository.ComentarioRepository;
import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.poema.service.PoemaIService;
import br.com.ifba.usuario.service.UsuarioIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/*
 * Serviço que gerencia a lógica de negócio para a entidade Comentário.
 */
@Service
@RequiredArgsConstructor
public class ComentarioService implements ComentarioIService {

    private final ComentarioRepository comentarioRepository;
    private final PoemaIService poemaService;
    private final UsuarioIService usuarioService;

    @Override
    @Transactional
    public Comentario save(Comentario comentario) {
        comentario.setDataPublicacao(LocalDate.now());
        comentario.setPoema(poemaService.findById(comentario.getPoema().getId()));
        comentario.setUsuario(usuarioService.findById(comentario.getUsuario().getId()));
        return comentarioRepository.save(comentario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comentario> findAll() {
        return comentarioRepository.findAll();
    }

    @Override
    @Transactional
    public Comentario update(Comentario comentario) {
        if (!comentarioRepository.existsById(comentario.getId())) {
            throw new BusinessException("Comentário não encontrado.");
        }
        comentario.setPoema(poemaService.findById(comentario.getPoema().getId()));
        comentario.setUsuario(usuarioService.findById(comentario.getUsuario().getId()));
        return comentarioRepository.save(comentario);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!comentarioRepository.existsById(id)) {
            throw new BusinessException("Comentário não encontrado.");
        }
        comentarioRepository.delete(this.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Comentario findById(Long id) {
        return comentarioRepository.findById(id).orElseThrow(
                () -> new BusinessException("Comentário não encontrado.")
        );
    }
}