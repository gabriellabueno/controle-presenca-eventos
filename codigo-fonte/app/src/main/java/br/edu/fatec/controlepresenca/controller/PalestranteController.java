package br.edu.fatec.controlepresenca.controller;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import br.edu.fatec.controlepresenca.model.PalestranteDAO;
import br.edu.fatec.controlepresenca.util.Palestrante;

public class PalestranteController {
    private PalestranteDAO dao;
    private Context context;

    // Construtor
    public PalestranteController(Context context) {
        this.context = context;
        this.dao = new PalestranteDAO(context); // Abre BD
    }

    public void create(Integer idEvento, Palestrante palestrante) {
        dao = new PalestranteDAO(context);
        dao.create(idEvento, palestrante);
    }

    public void update(Palestrante palestrante) {
        dao = new PalestranteDAO(context);
        dao.update(palestrante);
    }

    public void delete(Palestrante palestrante) {
        dao = new PalestranteDAO(context);
        dao.delete(palestrante);
    }

    public Palestrante read(String cpf) {
        dao = new PalestranteDAO(context);
        return dao.read(cpf);
    }

    public List<Palestrante> listAll() {
        return dao.listAll();
    }

    public void updateTable() {
        dao.updateTableID();
    }

    public void mostrarMensagem(String mensagem) {
        Toast.makeText(context, "Palestrante foi " + mensagem + " com sucesso",
                Toast.LENGTH_SHORT).show();
    }

}
