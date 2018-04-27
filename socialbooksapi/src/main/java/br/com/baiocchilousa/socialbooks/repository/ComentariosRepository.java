package br.com.baiocchilousa.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.baiocchilousa.socialbooks.domain.Comentario;

public interface ComentariosRepository extends JpaRepository<Comentario, Long>{

    
}
