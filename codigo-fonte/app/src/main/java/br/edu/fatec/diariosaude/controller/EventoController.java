package br.edu.fatec.diariosaude.controller;

import android.content.Context;
import android.widget.Toast;

import java.util.EventObject;
import java.util.List;

import br.edu.fatec.diariosaude.model.EventoDAO;
import br.edu.fatec.diariosaude.util.Pessoa;

public class EventoController {
    private EventoDAO dao;
    private Context context;

    // Construtor
    public EventoController(Context context) {
        this.context = context;
        this.dao = new EventoDAO(context); // Abre BD
    }

    public void create(Pessoa pessoa) {
        dao = new EventoDAO(context);
        dao.create(pessoa);
    }

    public void update(Pessoa pessoa) {
        dao = new EventoDAO(context);
        dao.update(pessoa);
    }

    public void delete(Pessoa pessoa) {
        dao = new EventoDAO(context);
        dao.delete(pessoa);
    }

    public Pessoa read(Integer id) {
        dao = new EventoDAO(context);
        return dao.read(id);
    }

    public List<Pessoa> listAll() {
        return dao.listAll();
    }

    public void updateTable() {
     //   dao.updateTableID();
    }

    public void mostrarMensagem(String mensagem) {
        Toast.makeText(context, "Pessoa foi " + mensagem + " com sucesso",
                Toast.LENGTH_SHORT).show();
    }

}
