package br.edu.fatec.diariosaude.util;

public class Responsavel {
    String tituloAcademico, biografia;

    public Responsavel() {
    }

    public Responsavel(String biografia, String tituloAcademico) {
        this.biografia = biografia;
        this.tituloAcademico = tituloAcademico;
    }

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
