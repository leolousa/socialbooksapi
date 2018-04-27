package br.com.baiocchilousa.socialbooks.services.exceptions;

public class LivroNaoEncontradoException extends RuntimeException{

    private static final long serialVersionUID = 2921559113843531698L;

    public LivroNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public LivroNaoEncontradoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
