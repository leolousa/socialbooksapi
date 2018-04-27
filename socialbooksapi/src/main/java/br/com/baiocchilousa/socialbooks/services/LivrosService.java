package br.com.baiocchilousa.socialbooks.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.baiocchilousa.socialbooks.domain.Comentario;
import br.com.baiocchilousa.socialbooks.domain.Livro;
import br.com.baiocchilousa.socialbooks.repository.ComentariosRepository;
import br.com.baiocchilousa.socialbooks.repository.LivrosRepository;
import br.com.baiocchilousa.socialbooks.services.exceptions.LivroNaoEncontradoException;

@Service
public class LivrosService {

    @Autowired
    private LivrosRepository livros;
    
    @Autowired
    private ComentariosRepository comentarios;
    
    public List<Livro> listar() {
        return livros.findAll();
    }
    
    public Livro buscar (Long id) {
        Livro livro = livros.findById(id).orElse(null);
        
        if(livro == null) {
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
    
    
    //Criado aqui mesmo para simplificar
    public Comentario salvarComentario(Long livroId, Comentario comentario) {
        Livro livro = buscar(livroId);
        comentario.setLivro(livro);
        comentario.setData(LocalDate.now());
        return comentarios.save(comentario);
    }
    
    public List<Comentario> listarComentarios(Long livroId) {
        //Verificar a existência
        Livro livro = buscar(livroId); //Se não existir a exception já está sendo tratada no Handler!
        return livro.getComentarios();
    }
    
    private void verificarExistencia(Livro livro) {
        buscar(livro.getId());
    }
}
