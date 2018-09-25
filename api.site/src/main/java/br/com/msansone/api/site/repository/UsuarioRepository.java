package br.com.msansone.api.site.repository;

import br.com.msansone.api.site.model.usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<usuario, Long> {

}