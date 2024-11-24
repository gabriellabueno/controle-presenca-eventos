package br.edu.fatec.controlepresenca.util;

public class Participante {

    // ATRIBUTOS
    private String nome, cpf, email, curso;
    private Integer cargaHoraria; // Será utilizada somente se houver certificado

    // CONSTRUTORES
    public Participante() {
    }

    public Participante(String cpf, String nome, String email, String curso) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.curso = curso;
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

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }






    // Será utilizada somente se houver certificado

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
}
