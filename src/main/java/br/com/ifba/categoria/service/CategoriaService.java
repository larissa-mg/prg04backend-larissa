package br.com.ifba.categoria.service;

import br.com.ifba.categoria.entity.Categoria;
import br.com.ifba.categoria.repository.CategoriaRepository;
import br.com.ifba.infrastructure.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * Serviço que gerencia a lógica de negócio para a entidade Categoria.
 */
@Service
@RequiredArgsConstructor
public class CategoriaService implements CategoriaIService {

    private final CategoriaRepository categoriaRepository;

    @Override
    @Transactional
    public Categoria save(Categoria categoria) {
        if (categoria.getId() != null && categoriaRepository.existsById(categoria.getId())) {
            throw new BusinessException("Categoria já existente.");
        }
        return categoriaRepository.save(categoria);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    @Transactional
    public Categoria update(Categoria categoria) {
        if (!categoriaRepository.existsById(categoria.getId())) {
            throw new BusinessException("Categoria não encontrada.");
        }
        return categoriaRepository.save(categoria);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new BusinessException("Categoria não encontrada.");
        }
        categoriaRepository.delete(this.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria findById(Long id) {
        return categoriaRepository.findById(id).orElseThrow(
                () -> new BusinessException("Categoria não encontrada.")
        );
    }
}