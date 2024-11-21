package br.edu.fatec.diariosaude.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.fatec.diariosaude.util.Evento;
import br.edu.fatec.diariosaude.util.Palestrante;
import br.edu.fatec.diariosaude.util.Participante;

public class EventoDAO {
    // Variáveis de Conexão
    private ConnectionFactory conexao;
    private SQLiteDatabase banco;

    // Variáveis pra consultas SQL
    private static final String table = "tb_evento",
            id = "id_evento_pk",
            nome = "nome",
            data = "data",
            horaInicio = "horaInicio",
            horaFim = "horaFim",
            duracao = "duracao",
            local = "local",
            status = "status",
            organizador = "organizador";

    String[] args = {id, nome,};

    // Construtor
    public EventoDAO(Context context){
        // Abre conexão com BD
        conexao = new ConnectionFactory(context);
        banco = conexao.getWritableDatabase();
    }

    // MÉTODOS CRUD - CREATE, READ, UPDATE, DELETE

    // CREATE
    public void create(Evento evento){
        ContentValues values = new ContentValues();

        values.put(nome, evento.getNome());


        banco.insert(table, null, values);
    }

    // UPDATE
    public void update(Evento evento) {
        ContentValues values = new ContentValues();

        values.put(nome, evento.getNome());


        // String[] idPessoa = {String.valueOf(pessoa.getId())};

        // banco.update(table, values, "id=?", idPessoa);
    }

    // Reseta ID da tabela "pessoa"
    public void updateTableID() {
        banco.delete("sqlite_sequence", "name = ?", new String[]{table});
        banco.delete(table, null, null);
    }


    // DELETE
    public void delete(Evento evento) {
        //String[] idPessoa = {String.valueOf(pessoa.getId())};

        //banco.delete(table,"id=?", idPessoa);
    }


    // READ
    public Evento read(Integer id) {
        String[] idPessoa = {String.valueOf(id)};

        Cursor cursor = banco.query(table, args,
                "id=?", idPessoa, null, null, null);

        cursor.moveToFirst();

        Evento evento = new Evento();

        // Percorre todas as colunas do registro
        if(cursor.getCount() > 0){
            evento.setNome(cursor.getString(1));

        }
        cursor.close();
        return evento;
    }

    // Retorna uma lista com todos os registros
    // para apresentar no ListView
    public List<Evento> listAll() {
        List<Evento> eventos = new ArrayList<>(); // Array de eventos

        String[] args = {id, nome};

        Cursor cursor = banco.query(table, args,
                null, null, null, null, null );

        // Loop para percorrer todas as linhas da tabela
        while (cursor.moveToNext()) {
            Evento e = new Evento();

            //  p.setId(cursor.getInt(0));
            e.setNome(cursor.getString(1));
            eventos.add(e); // adiciona evento ao array
        }

        cursor.close();
        return eventos;
    }

}
