package br.com.ifba.poema.repository;

import br.com.ifba.poema.entity.Poema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * Repositório para a entidade Poema.
 */
@Repository
public interface PoemaRepository extends JpaRepository<Poema, Long> {
    List<Poema> findByPoetaId(Long poetaId);
}