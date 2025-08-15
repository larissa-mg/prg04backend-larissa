package br.com.ifba.comentario.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import br.com.ifba.poema.entity.Poema;
import br.com.ifba.usuario.entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

/*
 * Entidade JPA para representar a tabela 'comentarios' no banco de dados.
 * Relaciona o conteúdo do comentário com o poema e o usuário que o fez.
 */
@Entity
@Table(name = "comentarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Comentario extends PersistenceEntity implements Serializable {
    @Column(name = "conteudo", nullable = false)
    private String conteudo;

    @Column(name = "data_publicacao", nullable = false)
    private LocalDate dataPublicacao;

    // Relacionamento muitos para um: muitos comentários podem ser de um único poema.
    // @JoinColumn cria a coluna 'poema_id' na tabela 'comentarios'.
    @ManyToOne
    @JoinColumn(name = "poema_id")
    private Poema poema;

    // Relacionamento muitos para um: muitos comentários podem ser de um único usuário.
    // @JoinColumn cria a coluna 'usuario_id' na tabela 'comentarios'.
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
