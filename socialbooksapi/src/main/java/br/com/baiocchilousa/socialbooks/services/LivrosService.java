package br.com.baiocchilousa.socialbooks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.baiocchilousa.socialbooks.domain.Livro;
import br.com.baiocchilousa.socialbooks.repository.LivrosRepository;
import br.com.baiocchilousa.socialbooks.services.exceptions.LivroNaoEncontradoException;

@Service
public class LivrosService {

    @Autowired
    private LivrosRepository livros;
    
    public List<Livro> listar() {
        return livros.findAll();
    }
    
    public Optional<Livro> buscar (Long id) {
        Optional<Livro> livro = livros.findById(id);
        
        if(!livro.isPresent()) {
            //Não encontrou o recurso da requisição, retornando 404
            throw new LivroNaoEncontradoException("O livro não pode ser encontrado!");
        }
        
        return livro;
    }
    
    public void deletar(Long id) {
        try {
            livros.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new LivroNaoEncontradoException("O livro não pode ser encontrado!");
        }
    }

    public Livro salvar (Livro livro) {
        livro.setId(null);
        return livros.save(livro);
    }
    
    public void atualizar(Livro livro) {
        verificarExistencia(livro);
        livros.save(livro);
    }
    
    
    private void verificarExistencia(Livro livro) {
        buscar(livro.getId());
    }
}
