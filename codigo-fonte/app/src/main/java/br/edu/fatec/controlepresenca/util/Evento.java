package br.edu.fatec.controlepresenca.util;

import java.util.List;

public class Evento {

    //ATRIBUTOS
    private String nome, local, status, organizadores, palestrantes,
            data, horaInicio, horaFim;
    private List<Participante> participantes;
    private Integer id;

    //CONSTRUTORES
    public Evento() {}


    public Evento(String nome, String local, String status, String data, String horaInicio, String horaFim,
                  String palestrantes, String organizadores, List<Participante> participantes) {
        this.nome = nome;
        this.local = local;
        this.status = status;
        this.data = data;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.palestrantes = palestrantes;
        this.organizadores = organizadores;
        this.participantes = participantes;
    }

    //MÉTODO PARA FINALIZAR O EVENTO
    public void finalizaEvento() {
        status = "Encerrado";
    }

    //MÉTODO PARA ADICIONAR PARTICIPANTE NO EVENTO
    public void adicionaParticipante(Participante participante) {
        participantes.add(participante);
    }



    //GETTERS E SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public String getPalestrantes() {
        return palestrantes;
    }

    public void setPalestrantes(String palestrantes) {
        this.palestrantes = palestrantes;
    }

    public String getOrganizadores() {
        return organizadores;
    }

    public void setOrganizadores(String organizadores) {
        this.organizadores = organizadores;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
