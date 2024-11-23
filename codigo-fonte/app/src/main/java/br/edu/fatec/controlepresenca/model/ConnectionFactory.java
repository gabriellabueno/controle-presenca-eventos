package br.edu.fatec.controlepresenca.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConnectionFactory extends SQLiteOpenHelper {

    // Variáveis de configuração do BD
    private static final String NAME = "banco.db";
    private static final Integer VERSION = 1;

    // Construtor
    public ConnectionFactory(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTbEvento = "CREATE TABLE tb_evento (" +
                " id_evento_pk INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                " nome_evento VARCHAR(100) NOT NULL," +
                " data DATE NOT NULL," +
                " horaInicio TIME NOT NULL," +
                " horaFim TIME NOT NULL," +
                " duracao TIME NOT NULL," +
                " local VARCHAR(50) NOT NULL," +
                " status TINYINT NOT NULL," +
                " organizador VARCHAR(100) );";
        db.execSQL(createTbEvento);

        String createTbParticipante = "CREATE TABLE tb_participante (" +
                " cpf_participante_pk VARCHAR(15) NOT NULL PRIMARY KEY ," +
                " nome_participante VARCHAR(100) NOT NULL," +
                " email VARCHAR(100) NOT NULL," +
                " cargaHoraria TINYINT," +
                " curso VARCHAR(20));";
        db.execSQL(createTbParticipante);


        String createTbPalestrante = "CREATE TABLE tb_palestrante (" +
                " cpf_palestrante_pk VARCHAR(15) NOT NULL PRIMARY KEY," +
                " nome_responsavel VARCHAR(100) NOT NULL," +
                " email VARCHAR(100) NOT NULL," +
                " titulo VARCHAR(50)," +
                " biografia VARCHAR(255));";
        db.execSQL(createTbPalestrante);

        String createTbInscricao = "CREATE TABLE tb_inscricao (" +
                " id_inscricao INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                " id_evento_fk INTEGER NOT NULL," +
                " cpf_participante_fk VARCHAR(15) NOT NULL UNIQUE," +
                " FOREIGN KEY (id_evento_fk) REFERENCES tb_evento(id_evento_pk)" +
                " ON DELETE CASCADE ON UPDATE CASCADE," +
                " FOREIGN KEY (cpf_participante_fk) " +
                " REFERENCES tb_participante(cpf_participante_pk)" +
                " ON DELETE CASCADE ON UPDATE CASCADE);";
        db.execSQL(createTbInscricao);

        String createTbPalestra = "CREATE TABLE tb_palestra (" +
                " id_palestra INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                " id_evento_fk INTEGER NOT NULL," +
                " cpf_palestrante_fk VARCHAR(15) NOT NULL UNIQUE," +
                " FOREIGN KEY (id_evento_fk) REFERENCES tb_evento(id_evento_pk)" +
                " ON DELETE CASCADE ON UPDATE CASCADE," +
                " FOREIGN KEY (cpf_palestrante_fk)" +
                " REFERENCES tb_palestrante(cpf_palestrante_pk)" +
                " ON DELETE CASCADE ON UPDATE CASCADE);";
        db.execSQL(createTbPalestra);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTbEvento = "DROP TABLE IF EXISTS tb_evento",
                dropTbParticipante = "DROP TABLE IF EXISTS tb_participante",
                dropTbPalestrante = "DROP TABLE IF EXISTS tb_palestrante",
                dropTbInscricao = "DROP TABLE IF EXISTS tb_participacao",
                dropTbPalestra = "DROP TABLE IF EXISTS tb_palestra";

        db.execSQL(dropTbEvento);
        db.execSQL(dropTbParticipante);
        db.execSQL(dropTbPalestrante);
        db.execSQL(dropTbInscricao);
        db.execSQL(dropTbPalestra);
        onCreate(db);
    }


}