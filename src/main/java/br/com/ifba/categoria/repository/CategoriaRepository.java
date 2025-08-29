package br.com.ifba.categoria.repository;

import br.com.ifba.categoria.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * Repositório para a entidade Categoria.
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}