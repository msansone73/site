package br.com.msansone.api.site.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.engine.internal.Cascade;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "grupo")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class grupo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idgrupo;

    @NotBlank
    private String nome;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable( name = "usuariogrupo",
            joinColumns = {@JoinColumn(name="idgrupo")},
            inverseJoinColumns = {@JoinColumn(name="idusuario")})

    private Set<usuario> usuarios = new HashSet<usuario>(0);

}
