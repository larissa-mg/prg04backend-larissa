package br.com.ifba.poema.service;

import br.com.ifba.poema.entity.Poema;

import java.util.List;

/*
 * Interface de serviço para Poema.
 */
public interface PoemaIService {
    Poema save(Poema poema);
    List<Poema> findAll();
    Poema update(Poema poema);
    void delete(Long id);
    Poema findById(Long id);
    List<Poema> findByPoetaId(Long poetaId);
}