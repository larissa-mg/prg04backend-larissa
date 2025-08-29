package br.com.ifba.poema.service;

import br.com.ifba.categoria.service.CategoriaIService;
import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.poema.entity.Poema;
import br.com.ifba.poema.repository.PoemaRepository;
import br.com.ifba.poeta.service.PoetaIService;
import br.com.ifba.usuario.service.UsuarioIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PoemaService implements PoemaIService {

    private final PoemaRepository poemaRepository;
    private final PoetaIService poetaService;
    private final UsuarioIService usuarioService;
    private final CategoriaIService categoriaService;

    @Override
    @Transactional
    public Poema save(Poema poema) {
        this.setPoemaRelationships(poema);
        return poemaRepository.save(poema);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Poema> findAll() {
        return poemaRepository.findAll();
    }

    @Override
    @Transactional
    public Poema update(Poema poema) {
        if (!poemaRepository.existsById(poema.getId())) {
            throw new BusinessException("Poema não encontrado.");
        }
        this.setPoemaRelationships(poema);
        return poemaRepository.save(poema);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!poemaRepository.existsById(id)) {
            throw new BusinessException("Poema não encontrado.");
        }
        poemaRepository.delete(this.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Poema findById(Long id) {
        return poemaRepository.findById(id).orElseThrow(
                () -> new BusinessException("Poema não encontrado.")
        );
    }

    private void setPoemaRelationships(Poema poema) {
        poema.setPoeta(poetaService.findById(poema.getPoeta().getId()));
        poema.setUsuario(usuarioService.findById(poema.getUsuario().getId()));

        if (poema.getCategorias() != null) {
            poema.setCategorias(poema.getCategorias().stream()
                    .map(categoria -> categoriaService.findById(categoria.getId()))
                    .collect(Collectors.toList()));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Poema> findByPoetaId(Long poetaId) {
        return poemaRepository.findByPoetaId(poetaId);
    }
}