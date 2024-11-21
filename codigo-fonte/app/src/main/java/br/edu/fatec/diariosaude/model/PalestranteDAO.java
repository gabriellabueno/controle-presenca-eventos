package br.edu.fatec.diariosaude.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.diariosaude.util.Palestrante;

public class PalestranteDAO {
    // Variáveis de Conexão
    private ConnectionFactory conexao;
    private SQLiteDatabase banco;

    // Variáveis pra consultas SQL
    private static final String table = "pessoa";
    private static final String id = "id";
    private static final String nome = "nome";

    String[] args = {id, nome,};

    // Construtor
    public PalestranteDAO(Context context){
        // Abre conexão com BD
        conexao = new ConnectionFactory(context);
        banco = conexao.getWritableDatabase();
    }

    // MÉTODOS CRUD - CREATE, READ, UPDATE, DELETE

    // CREATE
    public void create(Palestrante pessoa){
        ContentValues values = new ContentValues();

        values.put(nome, pessoa.getNome());


        banco.insert(table, null, values);
    }

    // UPDATE
    public void update(Palestrante pessoa) {
        ContentValues values = new ContentValues();

        values.put(nome, pessoa.getNome());


       // String[] idPalestrante = {String.valueOf(pessoa.getId())};

       // banco.update(table, values, "id=?", idPalestrante);
    }

    // Reseta ID da tabela "pessoa"
    public void updateTableID() {
        banco.delete("sqlite_sequence", "name = ?", new String[]{table});
        banco.delete(table, null, null);
    }


    // DELETE
    public void delete(Palestrante pessoa) {
        //String[] idPalestrante = {String.valueOf(pessoa.getId())};

        //banco.delete(table,"id=?", idPalestrante);
    }


    // READ
    public Palestrante read(Integer id) {
        String[] idPalestrante = {String.valueOf(id)};

        Cursor cursor = banco.query(table, args,
                "id=?", idPalestrante, null, null, null);

        cursor.moveToFirst();

        Palestrante pessoa = new Palestrante();

        // Percorre todas as colunas do registro
        if(cursor.getCount() > 0){
            pessoa.setNome(cursor.getString(1));

        }
        cursor.close();
        return pessoa;
    }

    // Retorna uma lista com todos os registros
    // para apresentar no ListView
    public List<Palestrante> listAll() {
        List<Palestrante> pessoas = new ArrayList<>(); // Array de pessoas

        String[] args = {id, nome};

        Cursor cursor = banco.query(table, args,
                null, null, null, null, null );

        // Loop para percorrer todas as linhas da tabela
        while (cursor.moveToNext()) {
            Palestrante p = new Palestrante();

          //  p.setId(cursor.getInt(0));
            p.setNome(cursor.getString(1));
            pessoas.add(p); // adiciona pessoa ao array
        }

        cursor.close();
        return pessoas;
    }

}
