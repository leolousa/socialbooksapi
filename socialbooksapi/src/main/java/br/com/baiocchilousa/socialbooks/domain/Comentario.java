package br.com.baiocchilousa.socialbooks.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Comentario {

    @JsonInclude(Include.NON_NULL)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "O comentário deve ser preenchido.")
    @Size(max = 1500, message = "O comentário não pode conter mais de 1.500 caracteres.")
    @JsonProperty("comentario") //Muda o nome da propriedade no JSON.
    private String texto;
    
    @JsonInclude(Include.NON_NULL)
    private String usuario;
    
    @JsonInclude(Include.NON_NULL)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livro_id")
    @JsonIgnore //Anotação que evita a chamada cíclica do objeto indefinidamente (Não tenta listar os Livro - Loop infinito!)
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    
    
}
