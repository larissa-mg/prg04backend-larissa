package br.com.ifba.comentario.repository;

import br.com.ifba.comentario.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * Repositório para a entidade Comentário.
 */
@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}