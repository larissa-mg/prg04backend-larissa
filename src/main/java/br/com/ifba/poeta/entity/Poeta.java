package br.com.ifba.poeta.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import br.com.ifba.poema.entity.Poema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/*
 * Entidade JPA que representa a tabela 'poetas' no banco de dados.
 * Contém informações sobre o poeta e o relacionamento com seus poemas.
 */
@Entity
@Table(name = "poetas")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Poeta extends PersistenceEntity implements Serializable {
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "biografia")
    private String biografia;

    // Relacionamento um para muitos: um poeta pode ter muitos poemas
    @OneToMany(mappedBy = "poeta")
    private List<Poema> poemas;
}