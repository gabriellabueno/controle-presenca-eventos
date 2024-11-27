package br.edu.fatec.controlepresenca.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;
import java.sql.Time;
import java.sql.ResultSet;
import java.time.LocalTime;
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
            hora_inicio = "hora_inicio",
            hora_fim = "hora_fim",
            local = "local",
            status = "status",
            palestrante = "palestrante",
            organizador = "organizador";

    String[] args = {id, nome, data, hora_inicio, hora_fim, local, status, palestrante, organizador};

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
        values.put(data, evento.getData());
        values.put(hora_inicio, evento.getHoraInicio());
        values.put(hora_fim, evento.getHoraFim());
        values.put(local, evento.getLocal());
        values.put(status, evento.getStatus());
        values.put(palestrante, evento.getPalestrantes());
        values.put(organizador, evento.getOrganizadores());

        banco.insert(table, null, values);
    }

    // UPDATE
    public void update(Evento evento) {
        ContentValues values = new ContentValues();

        values.put(nome, evento.getNome());
        values.put(data, evento.getData());
        values.put(hora_inicio, evento.getHoraInicio());
        values.put(hora_fim, evento.getHoraFim());
        values.put(local, evento.getLocal());
        values.put(palestrante, evento.getPalestrantes());
        values.put(organizador, evento.getOrganizadores());

        String[] idEvento = {String.valueOf(evento.getId())};

        banco.update(table, values,  id + "=?" , idEvento);
    }

    public void updateStatus(Integer idInput) {
        ContentValues values = new ContentValues();

        values.put(status, "Encerrado");

        String[] idEvento = {String.valueOf(idInput)};

        banco.update(table, values,  id + "=?" , idEvento);
    }

    // Reseta ID da tabela "evento"
    /*
    public void updateTableID() {
        banco.delete("sqlite_sequence", "id_evento_pk=?", new String[]{table});
        banco.delete(table, null, null);
    }
     */


    // DELETE
    public void delete(Evento evento) {
        String[] idEvento = {String.valueOf(evento.getId())};

        banco.delete(table,id + "=?", idEvento);
    }


    // READ
    public Evento read(Integer idInput) {
        String[] idEvento = {String.valueOf(idInput)};

        Cursor cursor = banco.query(table, args, id + "=?", idEvento,
                null, null, null);

        cursor.moveToFirst();

        Evento evento = new Evento();

        // Percorre todas as colunas do registro
        if(cursor.getCount() > 0){
            evento.setNome(cursor.getString(1));
            evento.setData(cursor.getString(2));
            evento.setHoraInicio(cursor.getString(3));
            evento.setHoraFim(cursor.getString(4));
            evento.setLocal(cursor.getString(5));
            evento.setStatus(cursor.getString(6));
            evento.setPalestrantes(cursor.getString(7));
            evento.setOrganizadores(cursor.getString(8));
        }
        cursor.close();
        return evento;
    }

    // Retorna uma lista com todos os registros
    // para apresentar no ListView
    public List<Evento> listAll() {
        List<Evento> eventos = new ArrayList<>(); // Array de eventos

        String[] args = {id, nome, data, hora_inicio, hora_fim, local, status, palestrante, organizador};

        Cursor cursor = banco.query(table, args,
                null, null, null, null, null );

        // Loop para percorrer todas as linhas da tabela
        while (cursor.moveToNext()) {
            Evento e = new Evento();

            e.setId(cursor.getInt(0));
            e.setNome(cursor.getString(1));
            e.setData(cursor.getString(2));
            e.setHoraInicio(cursor.getString(3));
            e.setHoraFim(cursor.getString(4));
            e.setLocal(cursor.getString(5));
            e.setStatus(cursor.getString(6));
            e.setPalestrantes(cursor.getString(7));
            e.setOrganizadores(cursor.getString(8));

            eventos.add(e); // adiciona evento ao array
        }

        cursor.close();
        return eventos;
    }

}
