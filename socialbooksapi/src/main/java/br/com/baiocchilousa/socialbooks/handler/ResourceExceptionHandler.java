package br.com.baiocchilousa.socialbooks.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.baiocchilousa.socialbooks.domain.DetalhesErro;
import br.com.baiocchilousa.socialbooks.services.exceptions.AutorExistenteException;
import br.com.baiocchilousa.socialbooks.services.exceptions.AutorNaoEncontradoException;
import br.com.baiocchilousa.socialbooks.services.exceptions.LivroNaoEncontradoException;

/**
 * Classe que intercepta todas as Excessões da aplicação para tratamento
 * @author leolo
 *
 */

@ControllerAdvice
public class ResourceExceptionHandler {

    //Trata a excessão de Livro não encontrado
    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<DetalhesErro> handleLivroNaoEncontradoException
            (LivroNaoEncontradoException e, HttpServletRequest request) {
        
        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(404l);
        erro.setTitulo("O livro não pode ser encontrado!");
        erro.setMensagemDesenvolvedor("http://erros.socialbooks.com.br/404");
        erro.setTimestamp(System.currentTimeMillis());
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
    
    //Trata a excessão de Autor existente
    @ExceptionHandler(AutorExistenteException.class)
    public ResponseEntity<DetalhesErro> handleAutorExistenteException
            (AutorExistenteException e, HttpServletRequest request) {
        
        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(409l);
        erro.setTitulo("Autor já existente!");
        erro.setMensagemDesenvolvedor("http://erros.socialbooks.com.br/409");
        erro.setTimestamp(System.currentTimeMillis());
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }
    
    //Trata a excessão de Autor não encontrado
    @ExceptionHandler(AutorNaoEncontradoException.class)
    public ResponseEntity<DetalhesErro> handleAutorNaoEncontradoException
            (AutorNaoEncontradoException e, HttpServletRequest request) {
        
        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(404l);
        erro.setTitulo("O autor não pode ser encontrado!");
        erro.setMensagemDesenvolvedor("http://erros.socialbooks.com.br/404");
        erro.setTimestamp(System.currentTimeMillis());
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
    
    //Trata a excessão de integridade de dados - Ex Inserindo um Livro com um Autor que não existe
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DetalhesErro> handleDataIntegrityViolationException
            (DataIntegrityViolationException e, HttpServletRequest request) {
        
        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(400l);
        erro.setTitulo("Requisição inválida!");
        erro.setMensagemDesenvolvedor("http://erros.socialbooks.com.br/400");
        erro.setTimestamp(System.currentTimeMillis());
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
    
    //Trata a excessão de má formação do JSON
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<DetalhesErro> handleHttpMessageNotReadableException
            (HttpMessageNotReadableException e, HttpServletRequest request) {
        
        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(400l);
        erro.setTitulo("Requisição mal formada!");
        erro.setMensagemDesenvolvedor("http://erros.socialbooks.com.br/400");
        erro.setTimestamp(System.currentTimeMillis());
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
