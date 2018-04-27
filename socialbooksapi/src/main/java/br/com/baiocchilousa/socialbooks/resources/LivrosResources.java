package br.com.baiocchilousa.socialbooks.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.baiocchilousa.socialbooks.domain.Livro;
import br.com.baiocchilousa.socialbooks.services.LivrosService;
import br.com.baiocchilousa.socialbooks.services.exceptions.LivroNaoEncontradoException;

@RestController
@RequestMapping("/livros")
public class LivrosResources {
    
    @Autowired
    private LivrosService livrosService;

    //Lista todos os livros
    @GetMapping()
    public ResponseEntity<List<Livro>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(livrosService.listar());
    }
    
    //Busca um livro
    @GetMapping("/{id}")//@PathVariable: pega a variável da requisição coloca no atributo id
    public ResponseEntity<?> buscar(@PathVariable Long id) {

        Optional<Livro> livro = null;
        
        try {
            livro = livrosService.buscar(id);
        } catch (LivroNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(livro);
    }
    
    //Deletar o Livro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            //Tenta deletar o recurso
            livrosService.deletar(id);
        } catch (LivroNaoEncontradoException e) {
            //Retorna 404
            return ResponseEntity.notFound().build();
        }
        //Retorna No Content
        return ResponseEntity.noContent().build();
    }
    
    //Atualizar registro
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable Long id) {
        //Para ter certeza de que o que está sendo atualizado é o id do recurso e não o objeto passado
        livro.setId(id);
        
        try {
            livrosService.atualizar(livro);
        } catch (LivroNaoEncontradoException e) {
          //Retorna 404
            return ResponseEntity.notFound().build();
        }
        
        //Retorna No content
        return ResponseEntity.noContent().build();
    }
    
    //Salva o livro
    @PostMapping()//@RequestBody: pega o corpo da requisição e coloca no objeto Livro
    public ResponseEntity<Void> salvar(@RequestBody Livro livro) {
        livro = livrosService.salvar(livro);
        
        //Montamos a URI do recurso criado
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(livro.getId()).toUri();
        
        //Retornamos a URI e 201 Created
        return ResponseEntity.created(uri).build();
    }
}
