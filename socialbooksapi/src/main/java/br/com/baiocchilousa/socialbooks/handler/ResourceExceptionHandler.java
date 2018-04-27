package br.com.baiocchilousa.socialbooks.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.baiocchilousa.socialbooks.domain.DetalhesErro;
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
        erro.setTitulo("O Livro não pode ser encontrado!");
        erro.setMensagemDesenvolvedor("http://erros.socialbooks.com.br/404");
        erro.setTimestamp(System.currentTimeMillis());
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
    
}
