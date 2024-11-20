package br.edu.fatec.diariosaude.controller;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import br.edu.fatec.diariosaude.util.Pessoa;
import br.edu.fatec.diariosaude.model.PessoaDAO;

public class PessoaController {
    private PessoaDAO dao;
    private Context context;

    // Construtor
    public PessoaController(Context context) {
        this.context = context;
        this.dao = new PessoaDAO(context); // Abre BD
    }

    public void create(Pessoa pessoa) {
        dao = new PessoaDAO(context);
        dao.create(pessoa);
    }

    public void update(Pessoa pessoa) {
        dao = new PessoaDAO(context);
        dao.update(pessoa);
    }

    public void delete(Pessoa pessoa) {
        dao = new PessoaDAO(context);
        dao.delete(pessoa);
    }

    public Pessoa read(Integer id) {
        dao = new PessoaDAO(context);
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
