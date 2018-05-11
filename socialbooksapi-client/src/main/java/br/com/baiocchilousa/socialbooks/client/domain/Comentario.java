package br.com.baiocchilousa.socialbooks.client.domain;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Comentario {

    private Long id;
    
    @JsonProperty("comentario") //Muda o nome da propriedade no JSON.
    private String texto;
    
    private String usuario;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date data;
    
    private Livro livro;

    public Long getId() {
        return id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    
}
