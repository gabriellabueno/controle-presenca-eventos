package br.edu.fatec.controlepresenca.util;

import java.time.LocalTime;
import java.util.Date;

public class Evento {

    //ATRIBUTOS
    private String nome, local, organizadores;
    private Date data;
    private LocalTime horaInicio, horaFim;
    private Palestrante[] palestrantes;
    private Participante[] participantes;
    private Integer id, duracao, status;

    //CONSTRUTORES
    public Evento() {}


    public Evento(String nome, String local, Date data, LocalTime horaInicio, LocalTime horaFim,
                  Palestrante[] palestrantes, String organizadores, Participante[] participantes, Integer duracao, Integer status) {
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
    public Integer finalizaEvento() {
        return 1;
    }

    //MÉTODO PARA ADICIONAR PARTICIPANTE NO EVENTO
    public void adicionaParticipante(Participante participante) {
        
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

    public Palestrante[] getPalestrantes() {
        return palestrantes;
    }

    public void setPalestrantes(Palestrante[] palestrantes) {
        this.palestrantes = palestrantes;
    }

    public String getOrganizadores() {
        return organizadores;
    }

    public void setOrganizadores(String organizadores) {
        this.organizadores = organizadores;
    }

    public Participante[] getParticipantes() {
        return participantes;
    }

    public void setParticipantes(Participante[] participantes) {
        this.participantes = participantes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public Integer isStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
