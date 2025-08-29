package br.com.ifba.poeta.service;

import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.poeta.entity.Poeta;
import br.com.ifba.poeta.repository.PoetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * Serviço que gerencia a lógica de negócio para a entidade Poeta.
 */
@Service
@RequiredArgsConstructor
public class PoetaService implements PoetaIService {

    private final PoetaRepository poetaRepository;

    @Override
    @Transactional
    public Poeta save(Poeta poeta) {
        if (poeta.getId() != null && poetaRepository.existsById(poeta.getId())) {
            throw new BusinessException("Poeta já existente.");
        }
        return poetaRepository.save(poeta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Poeta> findAll() {
        return poetaRepository.findAll();
    }

    @Override
    @Transactional
    public Poeta update(Poeta poeta) {
        if (!poetaRepository.existsById(poeta.getId())) {
            throw new BusinessException("Poeta não encontrado.");
        }
        return poetaRepository.save(poeta);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!poetaRepository.existsById(id)) {
            throw new BusinessException("Poeta não encontrado.");
        }
        poetaRepository.delete(this.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Poeta findById(Long id) {
        return poetaRepository.findById(id).orElseThrow(
                () -> new BusinessException("Poeta não encontrado.")
        );
    }
}