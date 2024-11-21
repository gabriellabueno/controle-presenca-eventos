package br.edu.fatec.controlepresenca.util;

public class Participante {

    //ATRIBUTOS
    private String nome, cpf, email, curso;
    private Integer cargaHoraria;

    //CONSTRUTORES
    public Participante() {
        super();
    }

    public Participante(Integer cargaHoraria, String cpf, String curso, String email, String nome) {
        this.cargaHoraria = cargaHoraria;
        this.cpf = cpf;
        this.curso = curso;
        this.email = email;
        this.nome = nome;
    }

    //GETTERS E SETTERS

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
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
}
