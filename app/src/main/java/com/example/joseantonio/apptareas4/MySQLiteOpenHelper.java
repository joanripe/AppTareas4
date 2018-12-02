package com.example.joseantonio.apptareas4;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TABLE_CREATE = "CREATE TABLE tareas (" +
            "id INTEGER primary key  AUTOINCREMENT," +
            "nombre VARCHAR NOT NULL, " +
            "fecha VARCHAR NOT NULL, " +
            "hora VARCHAR NOT NULL);";
    private static final String DB_NAME = "tareas.db";
    private static final int DB_VERSION = 1;

    public MySQLiteOpenHelper (Context _context){
        super(_context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void guardar(String _nombre, String _fecha, String _hora){
        getReadableDatabase().execSQL("INSERT INTO tareas VALUES (" +
                ""+null+", " +
                "'" + _nombre + "', " +
                "'" + _fecha + "', " +
                "'" + _hora + "'" +
                ");");
    }

    public Cursor getTareas(){
        return getReadableDatabase().query("tareas", null, null, null, null, null, "fecha, hora asc");
    }

    public void Borrar (int id){
        getReadableDatabase().execSQL("DELETE FROM tareas WHERE id=" + id +";");
    }
}
