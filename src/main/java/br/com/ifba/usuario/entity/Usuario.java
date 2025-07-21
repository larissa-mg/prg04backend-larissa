package br.com.ifba.usuario.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

/*
* Entidade JPA que representa a tabela usuarios no banco de dados,
* com campos obrigatórios (nome, email único e senha),
* herda ID automático da PersistenceEntity
*/
@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Usuario extends PersistenceEntity implements Serializable {
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;
}
