package br.edu.fatec.controlepresenca.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.controlepresenca.util.Participante;

public class ParticipanteDAO {
    // Variáveis de Conexão
    private ConnectionFactory conexao;
    private SQLiteDatabase banco;

    // Variáveis pra consultas SQL
    private static final String
            table = "tb_participante",
            cpf = "cpf_participante_pk",
            nome = "nome",
            email = "email",
            curso = "curso",
            cargaHoraria = "cargaHoraria"; // Será utilizada somente se houver certificado

    String[] args = {cpf, nome, email, curso};

    // Construtor
    public ParticipanteDAO(Context context){
        // Abre conexão com BD
        conexao = new ConnectionFactory(context);
        banco = conexao.getWritableDatabase();
    }

    // MÉTODOS CRUD - CREATE, READ, UPDATE, DELETE

    // CREATE
    // Insere novo cadastro na tabela "participante"
    public void create(Integer idEvento, Participante participante){
        ContentValues values = new ContentValues();

        values.put(cpf, participante.getCpf());
        values.put(nome, participante.getNome());
        values.put(email, participante.getEmail());
        values.put(curso, participante.getCurso());
        banco.insert(table, null, values);

        inscreveParticipante(idEvento, participante.getCpf());
    }

    // Insere participante na tabela "inscricao"
    // linka participante e evento
    public void inscreveParticipante(Integer idEvento, String cpfParticipante){
        ContentValues values = new ContentValues();

        values.put("id_evento_fk", idEvento);
        values.put("cpf_participante_fk", cpfParticipante);

        banco.insert("tb_inscricao", null, values);
    }

    // UPDATE
    public void update(Participante participante) {
        ContentValues values = new ContentValues();

        values.put(nome, participante.getNome());
        values.put(email, participante.getEmail());
        values.put(curso, participante.getCurso());

       String[] cpfParticipante = {String.valueOf(participante.getCpf())};

       banco.update(table, values, cpf + "=?", cpfParticipante);
    }

    // DELETE
    public void delete(Participante participante) {
        String[] idParticipante = {String.valueOf(participante.getCpf())};

        banco.delete(table,cpf + "=?", idParticipante);
    }


    // READ
    public Participante read(String cpfInput) {
        String[] cpfParticipante = {cpfInput};

        Cursor cursor = banco.query(table, args,
                cpf + "=?", cpfParticipante, null, null, null);


        cursor.moveToFirst();

        Participante participante = new Participante();

        // Percorre todas as colunas do registro
        if(cursor.getCount() > 0){
            participante.setCpf(cursor.getString(1));
            participante.setNome(cursor.getString(2));
            participante.setEmail(cursor.getString(3));
            participante.setCurso(cursor.getString(4));
        }

        cursor.close();
        return participante;
    }

    // Retorna uma lista com todos os registros
    // para apresentar no ListView
    public List<Participante> listAll(Integer id) {
        List<Participante> participantes = new ArrayList<>();

        String[] idEvento = { String.valueOf(id) };

        Cursor cursor = banco.rawQuery(
                    "SELECT cpf_participante_pk, nome, email, curso " +
                        "FROM tb_participante " +
                        "INNER JOIN tb_inscricao " +
                        "ON tb_participante.cpf_participante_pk = tb_inscricao.cpf_participante_fk " +
                        "WHERE tb_inscricao.id_evento_fk = ?", idEvento
        );

        // Loop para percorrer todas as linhas da tabela
        while (cursor.moveToNext()) {
            Participante p = new Participante();

            p.setCpf(cursor.getString(cursor.getColumnIndexOrThrow(cpf)));
            p.setNome(cursor.getString(cursor.getColumnIndexOrThrow(nome)));
            p.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(email)));
            p.setCurso(cursor.getString(cursor.getColumnIndexOrThrow(curso)));
            participantes.add(p); // adiciona pessoa ao array
        }

        cursor.close();
        return participantes;
    }

}
