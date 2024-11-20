package br.edu.fatec.diariosaude.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.diariosaude.util.Pessoa;

public class PessoaDAO {
    // Variáveis de Conexão
    private ConnectionFactory conexao;
    private SQLiteDatabase banco;

    // Variáveis pra consultas SQL
    private static final String table = "pessoa";
    private static final String id = "id";
    private static final String nome = "nome";

    String[] args = {id, nome,};

    // Construtor
    public PessoaDAO(Context context){
        // Abre conexão com BD
        conexao = new ConnectionFactory(context);
        banco = conexao.getWritableDatabase();
    }

    // MÉTODOS CRUD - CREATE, READ, UPDATE, DELETE

    // CREATE
    public void create(Pessoa pessoa){
        ContentValues values = new ContentValues();

        values.put(nome, pessoa.getNome());


        banco.insert(table, null, values);
    }

    // UPDATE
    public void update(Pessoa pessoa) {
        ContentValues values = new ContentValues();

        values.put(nome, pessoa.getNome());


       // String[] idPessoa = {String.valueOf(pessoa.getId())};

       // banco.update(table, values, "id=?", idPessoa);
    }

    // Reseta ID da tabela "pessoa"
    public void updateTableID() {
        banco.delete("sqlite_sequence", "name = ?", new String[]{table});
        banco.delete(table, null, null);
    }


    // DELETE
    public void delete(Pessoa pessoa) {
        //String[] idPessoa = {String.valueOf(pessoa.getId())};

        //banco.delete(table,"id=?", idPessoa);
    }


    // READ
    public Pessoa read(Integer id) {
        String[] idPessoa = {String.valueOf(id)};

        Cursor cursor = banco.query(table, args,
                "id=?", idPessoa, null, null, null);

        cursor.moveToFirst();

        Pessoa pessoa = new Pessoa();

        // Percorre todas as colunas do registro
        if(cursor.getCount() > 0){
            pessoa.setNome(cursor.getString(1));

        }
        cursor.close();
        return pessoa;
    }

    // Retorna uma lista com todos os registros
    // para apresentar no ListView
    public List<Pessoa> listAll() {
        List<Pessoa> pessoas = new ArrayList<>(); // Array de pessoas

        String[] args = {id, nome};

        Cursor cursor = banco.query(table, args,
                null, null, null, null, null );

        // Loop para percorrer todas as linhas da tabela
        while (cursor.moveToNext()) {
            Pessoa p = new Pessoa();

          //  p.setId(cursor.getInt(0));
            p.setNome(cursor.getString(1));
            pessoas.add(p); // adiciona pessoa ao array
        }

        cursor.close();
        return pessoas;
    }

}
