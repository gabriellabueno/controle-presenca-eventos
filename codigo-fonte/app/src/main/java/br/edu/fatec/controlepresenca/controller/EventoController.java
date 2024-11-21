package br.edu.fatec.controlepresenca.controller;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import br.edu.fatec.controlepresenca.model.EventoDAO;
import br.edu.fatec.controlepresenca.util.Evento;

public class EventoController {
    private EventoDAO dao;
    private Context context;

    // Construtor
    public EventoController(Context context) {
        this.context = context;
        this.dao = new EventoDAO(context); // Abre BD
    }

    public void create(Evento evento) {
        dao = new EventoDAO(context);
        dao.create(evento);
    }

    public void update(Evento evento) {
        dao = new EventoDAO(context);
        dao.update(evento);
    }

    public void delete(Evento evento) {
        dao = new EventoDAO(context);
        dao.delete(evento);
    }

    public Evento read(Integer id) {
        dao = new EventoDAO(context);
        return dao.read(id);
    }

    public List<Evento> listAll() {
        return dao.listAll();
    }

    public void updateTable() {
        dao.updateTableID();
    }

    public void mostrarMensagem(String mensagem) {
        Toast.makeText(context, "Evento foi " + mensagem + " com sucesso",
                Toast.LENGTH_SHORT).show();
    }

}
