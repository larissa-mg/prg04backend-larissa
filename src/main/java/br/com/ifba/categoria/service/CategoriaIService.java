package br.com.ifba.categoria.service;

import br.com.ifba.categoria.entity.Categoria;

import java.util.List;

/*
 * Interface de serviço para Categoria.
 */
public interface CategoriaIService {
    Categoria save(Categoria categoria);
    List<Categoria> findAll();
    Categoria update(Categoria categoria);
    void delete(Long id);
    Categoria findById(Long id);
}