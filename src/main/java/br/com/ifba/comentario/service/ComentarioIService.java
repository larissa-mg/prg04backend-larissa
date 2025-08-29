package br.com.ifba.comentario.service;

import br.com.ifba.comentario.entity.Comentario;

import java.util.List;

/*
 * Interface de serviço para Comentário.
 */
public interface ComentarioIService {
    Comentario save(Comentario comentario);
    List<Comentario> findAll();
    Comentario update(Comentario comentario);
    void delete(Long id);
    Comentario findById(Long id);
}