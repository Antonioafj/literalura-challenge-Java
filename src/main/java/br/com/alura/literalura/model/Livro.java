package br.com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String titulo;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "autor_id")
        private Autor autor;

        @ElementCollection
        @CollectionTable(name = "livro_idiomas", joinColumns = @JoinColumn(name = "livro_id"))
        @Column(name = "idioma")
        private List<String> idiomas = new ArrayList<>();

        private Integer downloads;



    public Livro() {
    }

    public Livro(String titulo, Autor autor , List<String> idiomas, Integer downloads) {
        this.titulo = titulo;
        this.autor = autor;
        this.idiomas = idiomas;
        this.downloads = downloads;
    }

    public Livro(DadosLivro dadosLivro) {
        this.titulo = dadosLivro.titulo();

        if (dadosLivro.autores() != null && !dadosLivro.autores().isEmpty()) {
                  this.autor = new Autor(dadosLivro.autores().get(0).nome(), dadosLivro.autores().get(0).anoNascimento(), dadosLivro.autores().get(0).anoFalecimento());
                }
        this.idiomas = dadosLivro.idiomas();
        this.downloads = dadosLivro.downloads();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public java.lang.String toString() {
        return  " titulo= " + titulo +
                " Autor =" +(autor != null ? autor.getNome() : "Desconhecido")+
                " idiomas= " + idiomas +
                " downloads= " + downloads;
    }
}
