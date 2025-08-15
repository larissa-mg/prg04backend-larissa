package br.com.ifba.categoria.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import br.com.ifba.poema.entity.Poema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/*
 * Entidade JPA para representar a tabela 'categorias' no banco de dados.
 * É usada para agrupar poemas por tema.
 */
@Entity
@Table(name = "categorias")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Categoria extends PersistenceEntity implements Serializable {
    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    // Relacionamento muitos para muitos: uma categoria pode ter muitos poemas, e um poema pode ter muitas categorias.
    @ManyToMany(mappedBy = "categorias")
    private List<Poema> poemas;
}