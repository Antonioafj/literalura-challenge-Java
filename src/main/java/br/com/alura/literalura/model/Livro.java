package br.com.alura.literalura.model;

import java.util.ArrayList;
import java.util.List;

public class Livro {
        private String titulo;
        private List<DadosAutor> autores = new ArrayList<>();
        private List<String> idiomas = new ArrayList<>();
        private Integer downloads;


    public Livro() {
    }

    public Livro(String titulo, List<DadosAutor> autores , List<String> idiomas, Integer downloads) {
        this.titulo = titulo;
        this.autores = autores;
        this.idiomas = idiomas;
        this.downloads = downloads;
    }

    public Livro(DadosLivro dadosLivro) {
        this.titulo = dadosLivro.titulo();
        this.autores = dadosLivro.autores();
        this.idiomas = dadosLivro.idiomas();
        this.downloads = dadosLivro.downloads();
    }

    public java.lang.String getTitulo() {
        return titulo;
    }

    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    public List<java.lang.String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<java.lang.String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public List<DadosAutor> getAutores() {
        return autores;
    }

    public void setAutores(List<DadosAutor> autores) {
        this.autores = autores;
    }

    @Override
    public java.lang.String toString() {
        return  " titulo= " + titulo +
                " autores= " + autores+
                " idiomas= " + idiomas +
                " downloads= " + downloads;
    }
}
