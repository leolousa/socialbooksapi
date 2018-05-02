package br.com.baiocchilousa.socialbooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.baiocchilousa.socialbooks.domain.Autor;
import br.com.baiocchilousa.socialbooks.repository.AutoresRepository;
import br.com.baiocchilousa.socialbooks.services.exceptions.AutorExistenteException;
import br.com.baiocchilousa.socialbooks.services.exceptions.AutorNaoEncontradoException;

@Service
public class AutoresService {

    @Autowired
    private AutoresRepository autores;
    
    public List<Autor> listar() {
        return autores.findAll();
    }
    
    public Autor buscar(Long id) {
        Autor autor = autores.findById(id).orElse(null);
        
        if(autor == null) {
            //Não encontrou o recurso da requisição, retornando 404
            throw new AutorNaoEncontradoException("O autor não pode ser encontrado!");
        }
        
        return autor;
    }
    
    public Autor salvar(Autor autor) {
        //Verifica se o autor passado já existe, já tem um id
        if(autor.getId() != null) {
            Autor a = autores.getOne(autor.getId());
            if(a != null) {
                throw new AutorExistenteException("O autor já existe!"); 
            }
        }
        return autores.save(autor);
    }
    
    
    
}
