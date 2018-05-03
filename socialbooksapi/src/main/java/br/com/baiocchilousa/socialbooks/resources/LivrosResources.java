package br.com.baiocchilousa.socialbooks.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
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

import br.com.baiocchilousa.socialbooks.domain.Comentario;
import br.com.baiocchilousa.socialbooks.domain.Livro;
import br.com.baiocchilousa.socialbooks.services.LivrosService;

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
        Livro livro = livrosService.buscar(id);
        
        //Cache com 20 segundos do tempo de expiração (fica em cache no cliente por 20 segundos)
        CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
        
        return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(livro);
    }
    
    //Deletar o Livro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        livrosService.deletar(id);
        //Retorna No Content(Sem conteúdo)
        return ResponseEntity.noContent().build();
    }
    
    //Atualizar registro
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@Valid @RequestBody Livro livro, @PathVariable Long id) {
        //Para ter certeza de que o que está sendo atualizado é o id do recurso e não o objeto passado
        livro.setId(id);
        livrosService.atualizar(livro);
        //Retorna No content
        return ResponseEntity.noContent().build();
    }
    
    //Salva o livro
    @PostMapping()//@RequestBody: pega o corpo da requisição e coloca no objeto Livro
    public ResponseEntity<Void> salvar(@Valid @RequestBody Livro livro) {
        livro = livrosService.salvar(livro);
        
        //Montamos a URI do recurso criado
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(livro.getId()).toUri();
        
        //Retornamos a URI e 201 Created
        return ResponseEntity.created(uri).build();
    }
    
    //Adiciona comentários (Como não será utilizado em nenhuma outra parte da aplicação criamos aqui mesmo!)
    @PostMapping("/{id}/comentarios")
    public ResponseEntity<Void> adcionarComentario(@PathVariable("id") Long livroId, @RequestBody Comentario comentario) {
        livrosService.salvarComentario(livroId, comentario);
        
        //Montamos a URI do recurso criado (Busca todos os comentários por vez)
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        
        //Retornamos a URI e 201 Created
        return ResponseEntity.created(uri).build();
    }
    
    @GetMapping("/{id}/comentarios")
    public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable("id") Long livroId) {
        List<Comentario> comentarios = livrosService.listarComentarios(livroId);
        return ResponseEntity.status(HttpStatus.OK).body(comentarios);
    }
}
