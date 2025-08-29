package br.com.ifba.poeta.service;

import br.com.ifba.poeta.entity.Poeta;

import java.util.List;

/*
 * Interface de serviço para Poeta.
 */
public interface PoetaIService {
    Poeta save(Poeta poeta);
    List<Poeta> findAll();
    Poeta update(Poeta poeta);
    void delete(Long id);
    Poeta findById(Long id);
}