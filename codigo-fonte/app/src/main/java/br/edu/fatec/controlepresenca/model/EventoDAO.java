package br.edu.fatec.controlepresenca.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.controlepresenca.util.Evento;


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

    String[] args = {id, nome, data, horaInicio, horaFim, duracao, local, status, organizador};

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
        values.put(data, String.valueOf(evento.getData()));
        values.put(horaInicio, String.valueOf(evento.getHoraInicio()));
        values.put(horaFim, String.valueOf(evento.getHoraFim()));
        values.put(duracao, String.valueOf(evento.getDuracao()));
        values.put(local, evento.getLocal());
        values.put(status, String.valueOf(evento.isStatus()));
        values.put(organizador, evento.getOrganizadores());

        banco.insert(table, null, values);
    }

    // UPDATE
    public void update(Evento evento) {
        ContentValues values = new ContentValues();

        values.put(nome, evento.getNome());
        values.put(data, String.valueOf(evento.getData()));
        values.put(horaInicio, String.valueOf(evento.getHoraInicio()));
        values.put(horaFim, String.valueOf(evento.getHoraFim()));
        values.put(duracao, String.valueOf(evento.getDuracao()));
        values.put(local, evento.getLocal());
        values.put(status, String.valueOf(evento.isStatus()));
        values.put(organizador, evento.getOrganizadores());

        String[] idEvento = {String.valueOf(evento.getId())};

        banco.update(table, values, id + "=?" , idEvento);
    }

    // Reseta ID da tabela "pessoa"
    public void updateTableID() {
        banco.delete("sqlite_sequence", nome + "=?", new String[]{table});
        banco.delete(table, null, null);
    }


    // DELETE
    public void delete(Evento evento) {
        String[] idEvento = {String.valueOf(evento.getId())};

        banco.delete(table,id + "=?", idEvento);
    }


    // READ
    public Evento read(Integer id) {
        String[] idEvento = {String.valueOf(id)};

        Cursor cursor = banco.query(table, args,
                EventoDAO.id + "=?", idEvento, null, null, null);

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

        String[] args = {id, nome, data, horaInicio, horaFim, duracao, local, status, organizador};

        Cursor cursor = banco.query(table, args,
                null, null, null, null, null );

        // Loop para percorrer todas as linhas da tabela
        while (cursor.moveToNext()) {
            Evento e = new Evento();

            e.setId(cursor.getInt(0));
            e.setNome(cursor.getString(1));
            //e.setData(); Arrumar tipo de data
            //e.setHoraInicio(); Arrumar tipo de data
            //e.setHoraFim(); Arrumar tipo de data
            e.setDuracao(cursor.getInt(5));
            e.setLocal(cursor.getString(6));
            e.setStatus(cursor.getInt(7));
            e.setOrganizadores(cursor.getString(8));

            eventos.add(e); // adiciona evento ao array
        }

        cursor.close();
        return eventos;
    }

}
