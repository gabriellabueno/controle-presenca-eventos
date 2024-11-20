package br.edu.fatec.diariosaude.model;

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
        String query = "CREATE TABLE pessoa(id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nome VARCHAR(100), "
                + "idade INTEGER, "
                + "altura DECIMAL(5,2), "
                + "peso DECIMAL(5,2), "
                + "genero VARCHAR(50), "
                + "sexo INTEGER, "
                + "gestante INTEGER, "
                + "sedentario INTEGER)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS pessoa";
        db.execSQL(query);
        onCreate(db);
    }


}
