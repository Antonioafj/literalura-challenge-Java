package br.com.alura.literalura.model;

import java.util.List;

public class Autor {

    private String nome;

    private Integer anoNascimento;

    private Integer anoFalecimento;

    private String livros;

    public Autor(String livros, DadosAutor dadosAutor ) {
        this.nome = dadosAutor.nome();
        this.anoNascimento = dadosAutor.anoNascimento();
        this.anoFalecimento = dadosAutor.anoFalecimento();
        this.livros = livros;
    }

    public Autor(String nome, Integer anoNascimento, Integer anoFalecimento) {
        this.nome = nome;
        this.anoNascimento = anoNascimento;
        this.anoFalecimento = anoFalecimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLivros() {
        return livros;
    }

    public void setLivros(String livros) {
        this.livros = livros;
    }

    @Override
    public java.lang.String toString() {
        return  " nome= " + nome +
                " Ano De Nascimento= " + anoNascimento +
                " Ano De Falecimento= " + anoFalecimento +
                " Livros= " + livros;

    }
}
