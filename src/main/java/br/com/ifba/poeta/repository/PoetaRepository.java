package br.com.ifba.poeta.repository;

import br.com.ifba.poeta.entity.Poeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * Interface de repositório que fornece métodos para operações CRUD
 * com a entidade Poeta.
 */
@Repository
public interface PoetaRepository extends JpaRepository<Poeta, Long> {

}