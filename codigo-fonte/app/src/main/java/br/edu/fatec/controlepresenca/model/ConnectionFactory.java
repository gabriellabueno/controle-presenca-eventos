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
                " nome VARCHAR(100) NOT NULL," +
                " data VARCHAR(10) NOT NULL," +
                " hora_inicio VARCHAR(5) NOT NULL," +
                " hora_fim VARCHAR(5) NOT NULL," +
                " local VARCHAR(50) NOT NULL," +
                " status VARCHAR(10) NOT NULL," +
                " palestrante VARCHAR(255) NOT NULL," +
                " organizador VARCHAR(100) NOT NULL);";
        db.execSQL(createTbEvento);

        String createTbParticipante = "CREATE TABLE tb_participante (" +
                " cpf_participante_pk VARCHAR(15) NOT NULL PRIMARY KEY ," +
                " nome VARCHAR(100) NOT NULL," +
                " email VARCHAR(100) NOT NULL," +
                " curso VARCHAR(20) NOT NULL," +
                " cargaHoraria TINYINT);";
        db.execSQL(createTbParticipante);

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTbEvento = "DROP TABLE IF EXISTS tb_evento",
                dropTbParticipante = "DROP TABLE IF EXISTS tb_participante",
                dropTbInscricao = "DROP TABLE IF EXISTS tb_participacao";

        db.execSQL(dropTbEvento);
        db.execSQL(dropTbParticipante);
        db.execSQL(dropTbInscricao);
        onCreate(db);
    }


}
