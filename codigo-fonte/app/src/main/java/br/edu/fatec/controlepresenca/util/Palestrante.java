package br.edu.fatec.controlepresenca.util;

public class Palestrante {

    //ATRIBUTOS
    private String nome, cpf, email, tituloAcademico, biografia;

    //CONSTRUTORES
    public Palestrante() {
        super();
    }

    public Palestrante(String biografia, String cpf, String email, String nome, String tituloAcademico) {
        this.biografia = biografia;
        this.cpf = cpf;
        this.email = email;
        this.nome = nome;
        this.tituloAcademico = tituloAcademico;
    }

    //GETTERS E SETTERS

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTituloAcademico() {
        return tituloAcademico;
    }

    public void setTituloAcademico(String tituloAcademico) {
        this.tituloAcademico = tituloAcademico;
    }
}
