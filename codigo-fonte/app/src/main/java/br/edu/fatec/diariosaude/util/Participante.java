package br.edu.fatec.diariosaude.util;

public class Participante extends Pessoa {

    //ATRIBUTOS
    private Integer cargaHoraria;
    private String curso;

    //CONSTRUTORES
    public Participante() {
        super();
    }

    public Participante(String nome, String cpf, String email, Integer cargaHoraria, String curso) {
        super(nome, cpf, email);
        this.cargaHoraria = cargaHoraria;
        this.curso = curso;
    }

    //GETTERS E SETTERS
    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
