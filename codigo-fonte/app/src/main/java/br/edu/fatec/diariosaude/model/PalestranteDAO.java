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
    private static final String
            table = "tb_palestrante",
             cpf = "cpf_palestrante_pk",
             nome = "nome_responsavel",
             email = "email",
             titulo = "titulo",
             biografia = "biografia";

    String[] args = {cpf, nome, email, titulo, biografia};

    // Construtor
    public PalestranteDAO(Context context){
        // Abre conexão com BD
        conexao = new ConnectionFactory(context);
        banco = conexao.getWritableDatabase();
    }

    // MÉTODOS CRUD - CREATE, READ, UPDATE, DELETE

    // CREATE
    // Insere novo cadastro na tabela "palestrante"
    public void create(Integer idEvento, Palestrante palestrante){
        ContentValues values = new ContentValues();

        values.put(cpf, palestrante.getCpf());
        values.put(nome, palestrante.getNome());
        values.put(email, palestrante.getEmail());
        values.put(titulo, palestrante.getTituloAcademico());
        values.put(biografia, palestrante.getBiografia());
        banco.insert(table, null, values);

        cadastraPalestrante(idEvento, palestrante.getCpf());
    }

    // Insere palestrante na tabela "palestra"
    // linka palestrante e evento
    public void cadastraPalestrante(Integer idEvento, String cpfPalestrante){
        ContentValues values = new ContentValues();

        values.put("id_evento_fk", String.valueOf(idEvento));
        values.put("cpf_palestrante_fk", cpfPalestrante);

        banco.insert("tb_palestra", null, values);
    }

    // UPDATE
    public void update(Palestrante palestrante) {
        ContentValues values = new ContentValues();

        values.put(nome, palestrante.getNome());
        values.put(email, palestrante.getEmail());
        values.put(titulo, palestrante.getTituloAcademico());
        values.put(biografia, palestrante.getBiografia());

       String[] idPalestrante = {String.valueOf(palestrante.getCpf())};

       banco.update(table, values, "cpf_palestrante_pk=?", idPalestrante);
    }

    // Reseta ID da tabela "palestrante"
    public void updateTableID() {
        banco.delete("sqlite_sequence", "name = ?", new String[]{table});
        banco.delete(table, null, null);
    }


    // DELETE
    public void delete(Palestrante palestrante) {
        String[] idPalestrante = {String.valueOf(palestrante.getCpf())};

        banco.delete(table,"cpf_palestrante_pk=?", idPalestrante);
    }


    // READ
    public Palestrante read(String cpf) {
        String[] idPalestrante = {cpf};

        Cursor cursor = banco.query(table, args,
                "cpf_palestrante_pk=?", idPalestrante, null, null, null);

        cursor.moveToFirst();

        Palestrante p = new Palestrante();

        // Percorre todas as colunas do registro
        if(cursor.getCount() > 0){
            p.setCpf(cursor.getString(1));
            p.setNome(cursor.getString(2));
            p.setEmail(cursor.getString(3));
            p.setTituloAcademico(cursor.getString(4));
            p.setBiografia(cursor.getString(5));
        }
        cursor.close();
        return p;
    }

    // Retorna uma lista com todos os registros
    // para apresentar no ListView
    public List<Palestrante> listAll() {
        List<Palestrante> palestrantes = new ArrayList<>(); // Array de palestrantes

        Cursor cursor = banco.query(table, args,
                null, null, null, null, null );

        // Loop para percorrer todas as linhas da tabela
        while (cursor.moveToNext()) {
            Palestrante p = new Palestrante();

            p.setCpf(cursor.getString(1));
            p.setNome(cursor.getString(2));
            p.setEmail(cursor.getString(3));
            p.setTituloAcademico(cursor.getString(4));
            p.setBiografia(cursor.getString(5));
            palestrantes.add(p);
        }

        cursor.close();
        return palestrantes;
    }

}
