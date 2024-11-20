package br.edu.fatec.diariosaude.util;

public class Responsavel extends Pessoa {

    //ATRIBUTOS
    String tituloAcademico, biografia;

    //CONSTRUTORES
    public Responsavel() {
        super();
    }

    public Responsavel(String nome, String cpf, String email, String biografia, String tituloAcademico) {
        super(nome, cpf, email);
        this.biografia = biografia;
        this.tituloAcademico = tituloAcademico;
    }

    //GETTERS E SETTERS
    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getTituloAcademico() {
        return tituloAcademico;
    }

    public void setTituloAcademico(String tituloAcademico) {
        this.tituloAcademico = tituloAcademico;
    }
}
