package br.com.baiocchilousa.socialbooks.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.baiocchilousa.socialbooks.domain.Livro;
import br.com.baiocchilousa.socialbooks.repository.LivrosRepository;

@RestController
@RequestMapping("/livros")
public class LivrosResources {
    
    @Autowired
    private LivrosRepository livros;

    //Lista os livros
    @GetMapping()
    public List<Livro> listar() {
        return livros.findAll();
    }
    
    //Busca um livro
    @GetMapping("/{id}")//@PathVariable: pega a variável da requisição coloca no atributo id
    public Livro buscar(@PathVariable Long id) {
        return livros.getOne(id);
    }
    
    //Deletar o Livro
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        livros.deleteById(id);
    }
    
    @PutMapping("/{id}")
    public void atualizar(@RequestBody Livro livro, @PathVariable Long id) {
        livro.setId(id);//Para ter certeza de que o que está sendo atualizado é o id do recurso e não o objeto passado
        livros.save(livro); // Faz um merge
    }
    
    //Salva o livro
    @PostMapping()//@RequestBody: pega o corpo da requisição e coloca no objeto Livro
    public void salvar(@RequestBody Livro livro) {
        livros.save(livro);
    }
}
