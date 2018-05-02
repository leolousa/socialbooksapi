package br.com.baiocchilousa.socialbooks.services.exceptions;

public class AutorNaoEncontradoException extends RuntimeException{

    private static final long serialVersionUID = -6276318579306176716L;

    public AutorNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public AutorNaoEncontradoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
