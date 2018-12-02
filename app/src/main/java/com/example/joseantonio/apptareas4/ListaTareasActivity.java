package com.example.joseantonio.apptareas4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaTareasActivity extends AppCompatActivity {

    ListView lvTareas;
    ArrayAdapter<Tarea> adapter;
    ImageView imgBack;

    ArrayList<String> tareasArray;
    MySQLiteOpenHelper db;
    Tarea nuevaTarea;
    ArrayList<Tarea> tareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tareas);

        db = new MySQLiteOpenHelper(this);
        tareasArray = new ArrayList<String>();

        obtenerDatos();

        imgBack = findViewById(R.id.imgBackListaTareas);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        lvTareas = findViewById(R.id.lvTareas);
        adapter = new ArrayAdapter<Tarea>(this, android.R.layout.simple_list_item_1, tareas);
        lvTareas.setAdapter(adapter);

        lvTareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(ListaTareasActivity.this);
                dialogo.setTitle("IMPORTANTE");
                dialogo.setMessage("¿eliminar la tarea?");
                dialogo.setCancelable(false);
                dialogo.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tareas.remove(position);
//                        db.Borrar(tareas.get(position).getId());
                        adapter.notifyDataSetChanged();
                    }
                });
                dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialogo.show();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void obtenerDatos (){
        int id;
        String nombre;
        String fecha;
        String hora;

        Cursor datos = db.getTareas();
        tareas = new ArrayList<Tarea>();

        while(datos.moveToNext()){
            id = datos.getInt(datos.getColumnIndex("id"));
            nombre = datos.getString(datos.getColumnIndex("nombre"));
            fecha = datos.getString(datos.getColumnIndex("fecha"));
            hora = datos.getString(datos.getColumnIndex("hora"));

            nuevaTarea = new Tarea(nombre, fecha, hora);
            tareas.add(nuevaTarea);
        }

    }



    public void EliminarTarea (AdapterView<?> adapterView, View view, int i) {
        final int posicion=i;

        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Importante");
        dialogo1.setMessage("¿ Elimina este teléfono ?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                tareasArray.remove(posicion);
                adapter.notifyDataSetChanged();
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
            }
        });
        dialogo1.show();

//        return false;
    }

}
