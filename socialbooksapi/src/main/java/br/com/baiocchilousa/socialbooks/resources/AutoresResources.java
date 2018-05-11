package br.com.baiocchilousa.socialbooks.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.baiocchilousa.socialbooks.domain.Autor;
import br.com.baiocchilousa.socialbooks.services.AutoresService;

@RestController
@RequestMapping("/autores")
public class AutoresResources {

    @Autowired
    private AutoresService autoresService;
    
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Autor>> listar() {
        List<Autor> autores = autoresService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(autores);
    }
    
    @PostMapping()
    public ResponseEntity<Void> salvar(@Valid @RequestBody Autor autor) {
        autor = autoresService.salvar(autor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscar(@PathVariable("id") Long id) {
        Autor autor = autoresService.buscar(id);
        return ResponseEntity.status(HttpStatus.OK).body(autor);
    }
}
