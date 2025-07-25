package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.BuscaLivro;
import br.com.alura.literalura.model.DadosAutor;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {


    private Scanner leitura = new Scanner(System.in);

    private final java.lang.String ENDERECO = "https://gutendex.com/books/?search=";

   private ConsumoApi consumoApi = new ConsumoApi();

   private ConverteDados converteDados = new ConverteDados();

    private String nomeLivro;

   private AutorRepository autorRepository;

    public LivroRepository livroRepository;


        public Principal(AutorRepository autorRepository, LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
    }

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
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }


    public void buscarLivroPorTitulo(){
                BuscaLivro buscaLivro = obterDados();

                if (buscaLivro == null|| buscaLivro.livro().isEmpty()){
                    System.out.println("Livro não encontrado.");
                    return;
                }

        DadosAutor dadosAutor = null;
                if (!buscaLivro.livro().get(0).autores().isEmpty()){
                    dadosAutor = buscaLivro.livro().get(0).autores().get(0);
                }

                Autor autor = null;

                if (dadosAutor != null){
                    Optional<Autor> autorExistente = autorRepository.findByNome(dadosAutor.nome());

                    if (autorExistente.isPresent()){
                        autor = autorExistente.get();
                        System.out.println("Autor: " + autor.getNome());
                    }else {
                        autor = new Autor(dadosAutor);
                        autorRepository.save(autor);
                    }
                }else {
                    System.out.println("Autor não encontrado");
                }

            Livro livro = new Livro(
                    buscaLivro.livro().get(0).titulo(),
                    autor,
                    buscaLivro.livro().get(0).idiomas(),
                    buscaLivro.livro().get(0).downloads()
            );
                livroRepository.save(livro);

                        System.out.println("\n--------LIVRO--------\n" +
                                           "Titulo: " + livro.getTitulo() +"\n"+
                                           "Autor: " + livro.getAutor().getNome()+"\n"+
                                           "Idioma " + (livro.getIdiomas().isEmpty() ? "N/A" : livro.getIdiomas().get(0)) +"\n"+
                                           "Número de downloads: " + livro.getDownloads() +"\n");


    }


    public BuscaLivro obterDados(){
        System.out.println( "Digite o nome do livro:");
        nomeLivro = leitura.nextLine();

        java.lang.String json = consumoApi.obterDados(ENDERECO + nomeLivro.replace(" ", "+").trim());

        BuscaLivro livro = converteDados.obterDados(json, BuscaLivro.class);


        return livro;

    }


    private void listarLivrosRegistrados() {

        List<Livro> livrosDoBanco = livroRepository.findAll();
        if (livrosDoBanco.isEmpty()) {
            System.out.println("Nenhum livro registrado ainda.");
            return;
        }
        livrosDoBanco.forEach(l ->
                System.out.println("\n--------LIVRO--------\n" +
                        "Titulo: " + l.getTitulo() +"\n"+
                        "Autor: " + (l.getAutor() != null ? l.getAutor().getNome() : "Autor Desconhecido")+"\n"+
                        "Idioma: " + (l.getIdiomas() != null && !l.getIdiomas().isEmpty() ? l.getIdiomas().get(0) : "N/A") + "\n"+
                        "Número de downloads: " + l.getDownloads() +"\n"));


    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();

        if(autores.isEmpty()){
            System.out.println("Não existe autor registrado");
        }
            autores.forEach(t -> {
            System.out.println("\n--------AUTOR--------\n" +
                    " Nome: " + t.getNome() + "\n" +
                    "Ano de Nascimento: " + t.getAnoNascimento() + "\n" +
                    "Ano de Falecimento: " + t.getAnoFalecimento() + "\n" +
                    "Livros: " + t.getLivros().stream()
                    .map(Livro::getTitulo)
                    .collect(Collectors.joining(", ")) + "\n");
        });
    }

    private void listarAutoresVivosAno() {
        System.out.println("Digite o ano que deseja pesquisar:");
        var ano = leitura.nextInt();

        List<Autor> autoresVivos = autorRepository.findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(ano, ano);

            if (autoresVivos.isEmpty()){
                System.out.println("Nenhum autor vivo encontrado");
                return;
            }

                autoresVivos.forEach(t ->
                System.out.println("\n--------AUTOR--------\n" +
                        "Nome: " + t.getNome() +"\n"+
                        "Ano de Nascimento: " + t.getAnoNascimento() +"\n"+
                        "Ano de Falecimento: " + t.getAnoFalecimento()+"\n"+
                        "Livros: " + t.getLivros().stream()
                        .map(Livro::getTitulo)
                        .collect(Collectors.joining(", ")) + "\n"));
    }

    private void listarLivrosIdioma() {
        System.out.println("Insira o idioma para realizar a pesquisa:");
        System.out.println("es - espanhol" +"\n"+
                           "en - ingles" +"\n"+
                           "fr - frances" +"\n"+
                           "pt - portuques" +"\n");
        var idioma = leitura.nextLine();

        List<Livro> livrosPorIdioma = livroRepository.findByIdiomasContaining(idioma.trim().toLowerCase());

        if (livrosPorIdioma.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o idioma '" + idioma + "' no banco de dados.");
            return;
        }
        livrosPorIdioma.forEach( l ->
                    System.out.println("\n--------LIVRO--------\n" +
                            "Titulo: " + l.getTitulo() + "\n" +
                            "Autor: " + (l.getAutor() != null ? l.getAutor().getNome() : "Autor Desconhecido") + "\n" +
                            "Idioma: " + (l.getIdiomas() != null && !l.getIdiomas().isEmpty() ? l.getIdiomas().get(0) : "N/A") + "\n" +
                            "Número de downloads: " + l.getDownloads() + "\n"));

    }
}





















