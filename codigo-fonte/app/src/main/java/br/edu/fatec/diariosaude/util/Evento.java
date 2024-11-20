package br.edu.fatec.diariosaude.util;

import java.time.LocalTime;
import java.util.Date;

public class Evento {

    //ATRIBUTOS
    private String nome, local;
    private Date data;
    private LocalTime horaInicio, horaFim;
    private Pessoa palestrantes[], organizadores[], participantes[];
    private Integer duracao;
    private boolean status;

    //CONSTRUTORES
    public Evento() {}


    public Evento(String nome, String local, Date data, LocalTime horaInicio, LocalTime horaFim, Pessoa[] palestrantes, Pessoa[] organizadores, Pessoa[] participantes, Integer duracao, boolean status) {
        this.nome = nome;
        this.local = local;
        this.data = data;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.palestrantes = palestrantes;
        this.organizadores = organizadores;
        this.participantes = participantes;
        this.duracao = duracao;
        this.status = status;
    }

    //MÉTODO PARA FINALIZAR O EVENTO
    public boolean finalizaEvento() {
        return true;
    }

    //MÉTODO PARA ADICIONAR PARTICIPANTE NO EVENTO
    public void adicionaParticipante(Pessoa participante) {
        
    }

    //GETTERS E SETTERS
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public Pessoa[] getPalestrantes() {
        return palestrantes;
    }

    public void setPalestrantes(Pessoa[] palestrantes) {
        this.palestrantes = palestrantes;
    }

    public Pessoa[] getOrganizadores() {
        return organizadores;
    }

    public void setOrganizadores(Pessoa[] organizadores) {
        this.organizadores = organizadores;
    }

    public Pessoa[] getParticipantes() {
        return participantes;
    }

    public void setParticipantes(Pessoa[] participantes) {
        this.participantes = participantes;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
