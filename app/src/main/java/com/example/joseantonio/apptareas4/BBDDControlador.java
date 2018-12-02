package com.example.joseantonio.apptareas4;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

public class BBDDControlador extends AppCompatActivity {

    public void nuevaTarea(String _nombre, String _fecha, String _hora){

        MySQLiteOpenHelper dbHelper = new MySQLiteOpenHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (dbHelper != null) {

            ContentValues registro = new ContentValues();

            registro.put("nombre", _nombre);
            registro.put("fecha", _fecha);
            registro.put("hora", _hora);

            // los inserto en la base de datos
            db.insert("tareas", null, registro);

            db.close();
        }
    }
}
