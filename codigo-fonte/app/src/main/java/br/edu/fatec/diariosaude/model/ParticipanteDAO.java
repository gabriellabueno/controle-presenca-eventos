package br.edu.fatec.diariosaude.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.diariosaude.util.Participante;

public class ParticipanteDAO {
    // Variáveis de Conexão
    private ConnectionFactory conexao;
    private SQLiteDatabase banco;

    // Variáveis pra consultas SQL
    private static final String table = "pessoa";
    private static final String id = "id";
    private static final String nome = "nome";

    String[] args = {id, nome,};

    // Construtor
    public ParticipanteDAO(Context context){
        // Abre conexão com BD
        conexao = new ConnectionFactory(context);
        banco = conexao.getWritableDatabase();
    }

    // MÉTODOS CRUD - CREATE, READ, UPDATE, DELETE

    // CREATE
    public void create(Participante pessoa){
        ContentValues values = new ContentValues();

        values.put(nome, pessoa.getNome());


        banco.insert(table, null, values);
    }

    // UPDATE
    public void update(Participante pessoa) {
        ContentValues values = new ContentValues();

        values.put(nome, pessoa.getNome());


       // String[] idParticipante = {String.valueOf(pessoa.getId())};

       // banco.update(table, values, "id=?", idParticipante);
    }

    // Reseta ID da tabela "pessoa"
    public void updateTableID() {
        banco.delete("sqlite_sequence", "name = ?", new String[]{table});
        banco.delete(table, null, null);
    }


    // DELETE
    public void delete(Participante pessoa) {
        //String[] idParticipante = {String.valueOf(pessoa.getId())};

        //banco.delete(table,"id=?", idParticipante);
    }


    // READ
    public Participante read(Integer id) {
        String[] idParticipante = {String.valueOf(id)};

        Cursor cursor = banco.query(table, args,
                "id=?", idParticipante, null, null, null);

        cursor.moveToFirst();

        Participante pessoa = new Participante();

        // Percorre todas as colunas do registro
        if(cursor.getCount() > 0){
            pessoa.setNome(cursor.getString(1));

        }
        cursor.close();
        return pessoa;
    }

    // Retorna uma lista com todos os registros
    // para apresentar no ListView
    public List<Participante> listAll() {
        List<Participante> pessoas = new ArrayList<>(); // Array de pessoas

        String[] args = {id, nome};

        Cursor cursor = banco.query(table, args,
                null, null, null, null, null );

        // Loop para percorrer todas as linhas da tabela
        while (cursor.moveToNext()) {
            Participante p = new Participante();

          //  p.setId(cursor.getInt(0));
            p.setNome(cursor.getString(1));
            pessoas.add(p); // adiciona pessoa ao array
        }

        cursor.close();
        return pessoas;
    }

}
