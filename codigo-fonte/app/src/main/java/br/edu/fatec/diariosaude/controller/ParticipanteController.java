package br.edu.fatec.diariosaude.controller;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import br.edu.fatec.diariosaude.model.ParticipanteDAO;
import br.edu.fatec.diariosaude.util.Participante;

public class ParticipanteController {
    private ParticipanteDAO dao;
    private Context context;

    // Construtor
    public ParticipanteController(Context context) {
        this.context = context;
        this.dao = new ParticipanteDAO(context); // Abre BD
    }

    public void create(Integer idEvento, Participante participante) {
        dao = new ParticipanteDAO(context);
        dao.create(idEvento, participante);
    }

    public void update(Participante participante) {
        dao = new ParticipanteDAO(context);
        dao.update(participante);
    }

    public void delete(Participante participante) {
        dao = new ParticipanteDAO(context);
        dao.delete(participante);
    }

    public Participante read(String cpf) {
        dao = new ParticipanteDAO(context);
        return dao.read(cpf);
    }

    public List<Participante> listAll() {
        return dao.listAll();
    }

    public void updateTable() {
        dao.updateTableID();
    }

    public void mostrarMensagem(String mensagem) {
        Toast.makeText(context, "Participante foi " + mensagem + " com sucesso",
                Toast.LENGTH_SHORT).show();
    }

}
