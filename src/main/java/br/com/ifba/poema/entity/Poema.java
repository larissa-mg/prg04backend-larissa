package br.com.ifba.poema.entity;

import br.com.ifba.categoria.entity.Categoria;
import br.com.ifba.comentario.entity.Comentario;
import br.com.ifba.infrastructure.entity.PersistenceEntity;
import br.com.ifba.poeta.entity.Poeta;
import br.com.ifba.usuario.entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/*
 * Entidade JPA que representa a tabela 'poemas' no banco de dados.
 * Contém o texto do poema e os relacionamentos com o poeta,
 * categorias, usuário que publicou e comentários.
 */
@Entity
@Table(name = "poemas")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Poema extends PersistenceEntity implements Serializable {
    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "conteudo", nullable = false, columnDefinition = "TEXT")
    private String conteudo;

    @Column(name = "data_publicacao", nullable = false)
    private Integer dataPublicacao;

    // Relacionamento muitos para um: muitos poemas podem ser de um único poeta.
    // @JoinColumn cria uma coluna 'poeta_id' na tabela 'poemas' para a chave estrangeira.
    @ManyToOne
    @JoinColumn(name = "poeta_id")
    private Poeta poeta;

    // Relacionamento Muitos-para-Um: muitos poemas podem ser enviados por um único usuário.
    // @JoinColumn cria uma coluna 'usuario_id' na tabela 'poemas' para a chave estrangeira.
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Relacionamento muitos para muitos: um poema pode ter muitas categorias, e uma categoria pode ter muitos poemas.
    // @JoinTable cria uma tabela de junção 'poema_categoria'.
    @ManyToMany
    @JoinTable(
            name = "poema_categoria",
            joinColumns = @JoinColumn(name = "poema_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<Categoria> categorias;

    // Relacionamento um para muitos: um poema pode ter muitos comentários.
    @OneToMany(mappedBy = "poema")
    private List<Comentario> comentarios;
}