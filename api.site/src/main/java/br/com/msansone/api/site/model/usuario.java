package br.com.msansone.api.site.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "usuario")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idusuario;

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    private String senha;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable( name = "usuariogrupo",
            joinColumns = {@JoinColumn(name="idusuario")},
            inverseJoinColumns = {@JoinColumn(name="idgrupo")})
    private Set<grupo> grupos = new HashSet<grupo>(0);



}

