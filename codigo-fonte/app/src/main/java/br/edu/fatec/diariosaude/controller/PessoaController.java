package br.edu.fatec.diariosaude.controller;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import br.edu.fatec.diariosaude.model.PalestranteDAO;

public class PessoaController {
    private PalestranteDAO dao;
    private Context context;

    // Construtor
    public PessoaController(Context context) {
        this.context = context;
        this.dao = new PalestranteDAO(context); // Abre BD
    }

    public void create(Pessoa pessoa) {
        dao = new PalestranteDAO(context);
        dao.create(pessoa);
    }

    public void update(Pessoa pessoa) {
        dao = new PalestranteDAO(context);
        dao.update(pessoa);
    }

    public void delete(Pessoa pessoa) {
        dao = new PalestranteDAO(context);
        dao.delete(pessoa);
    }

    public Pessoa read(Integer id) {
        dao = new PalestranteDAO(context);
        return dao.read(id);
    }

    public List<Pessoa> listAll() {
        return dao.listAll();
    }

    public void updateTable() {
        dao.updateTableID();
    }

    public void mostrarMensagem(String mensagem) {
        Toast.makeText(context, "Pessoa foi " + mensagem + " com sucesso",
                Toast.LENGTH_SHORT).show();
    }

}
