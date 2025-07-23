package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.BuscaLivro;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);

    private final String ENDERECO = "https://gutendex.com/books/?search=";

   private ConsumoApi consumoApi = new ConsumoApi();

   private ConverteDados converteDados = new ConverteDados();

   private List<Livro> livros = new ArrayList<>();

   private List<Autor> autor = new ArrayList<>();

   private String nomeLivro;


    public void exibeMenu(){
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1 - Busca livro por titulo
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em determinado ano
                    5 - Listar livros em um determinado idioma

                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroPorTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                     listarAutoresVivosAno();
                     break;
                case 5:
                      listarLivrosIdioma();
                     break;
                case 0:
                    System.out.println("Saindo...");
            }
        }
    }


    public void buscarLivroPorTitulo(){

        List<Livro> livro = obterDados().livro().stream()
                .map(d -> new Livro(d.titulo(), d.autores(), d.idiomas(), d.downloads()))
                .collect(Collectors.toList());


            Livro l = livro.get(0);

                        System.out.println("\n--------LIVRO--------\n" +
                                           "Titulo: " + l.getTitulo() +"\n"+
                                           "Autor: " + l.getAutores().get(0).nome() +"\n"+
                                           "Idioma " + l.getIdiomas().get(0) +"\n"+
                                           "Número de downloads: " + l.getDownloads() +"\n");


        this.livros.add(l);

    }


    public BuscaLivro obterDados(){
        System.out.println( "Digite o nome do livro:");
        nomeLivro = leitura.nextLine();

        String json = consumoApi.obterDados(ENDERECO + nomeLivro.replace(" ", "+").trim());

        BuscaLivro livro = converteDados.obterDados(json, BuscaLivro.class);


        return livro;

    }


    private void listarLivrosRegistrados() {
        if (this.livros.isEmpty()) {
            System.out.println("Nenhum livro registrado ainda.");
            return;
        }
            this.livros.forEach(l ->
                System.out.println("\n--------LIVRO--------\n" +
                        "Titulo: " + l.getTitulo() +"\n"+
                        "Autor: " + l.getAutores().get(0).nome() +"\n"+
                        "Idioma " + l.getIdiomas().get(0) +"\n"+
                        "Número de downloads: " + l.getDownloads() +"\n"));


    }

    private void listarAutoresRegistrados() {
        autor= livros.stream()
                .flatMap(l -> l.getAutores().stream())
                .map(a -> new Autor(a.nome(), a.anoNascimento(), a.anoFalecimento()))
                .collect(Collectors.toList());


                autor.forEach(t ->
                        System.out.println("\n--------LIVRO--------\n" +
                                "Titulo: " + t.getNome() +"\n"+
                                "Ano de Nascimento: " + t.getAnoNascimento() +"\n"+
                                "Ano de Falecimento: " + t.getAnoFalecimento()+"\n"+
                                "Livros: " + t.getLivros() +"\n"));

    }

    private void listarAutoresVivosAno() {
    }

    private void listarLivrosIdioma() {
    }
}
