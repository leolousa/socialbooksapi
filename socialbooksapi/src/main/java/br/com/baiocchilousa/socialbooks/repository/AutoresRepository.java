package br.com.baiocchilousa.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.baiocchilousa.socialbooks.domain.Autor;

public interface AutoresRepository extends JpaRepository<Autor, Long>{

    
}
