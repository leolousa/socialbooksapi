package br.com.baiocchilousa.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.baiocchilousa.socialbooks.domain.Livro;

public interface LivrosRepository extends JpaRepository<Livro, Long>{

    
}
