package br.edu.fatec.controlepresenca.util;

public class Palestrante {

    // ATRIBUTOS
    private String nome, cpf, email;

    // CONSTRUTORES
    public Palestrante() {
    }

    public Palestrante(String cpf, String nome, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }

    // GETTERS E SETTERS

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
