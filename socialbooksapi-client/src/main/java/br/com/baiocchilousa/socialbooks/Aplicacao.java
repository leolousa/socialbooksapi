package br.com.baiocchilousa.socialbooks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.baiocchilousa.socialbooks.client.LivrosClient;
import br.com.baiocchilousa.socialbooks.client.domain.Livro;
/**
 * Aplicação Simples em Java para autilizar a API Rest criada (socialbooksapi)
 * @author Leonardo
 *
 */
public class Aplicacao {
    
    public static void main(String[] args) throws ParseException {

        //Lista os livros do banco de dados
        LivrosClient cliente = new LivrosClient("http://localhost:8080", "usuario", "senha");
        List<Livro> listaLivros = cliente.listar();
        System.out.println("--- Lista de Livros ---");
        for(Livro livro : listaLivros) {
            System.out.println("Livro:" + livro.getNome());
        }

        //Salva um livro no banco de dados e retorna a URI
        Livro livro = new Livro();
        
        livro.setNome("GIT Passo-a-Passo");
        livro.setEditora("AlgaWorks");
        SimpleDateFormat publicacao = new SimpleDateFormat("dd/MM/yyyy");
        livro.setPublicacao(publicacao.parse("01/01/2018"));
        livro.setResumo("Este livro aborda técnicas de desenvolvimento com Git");
        
        String localizacao = cliente.salvar(livro);
        System.out.println("---------------------------");
        System.out.println("URI do livro salvo: " + localizacao);
        System.out.println("---------------------------");
        
        Livro livroBuscado = cliente.buscar(localizacao);
        
        System.out.println("Livro buscado: " + livroBuscado.getNome());
    }

}
