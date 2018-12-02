package com.example.joseantonio.apptareas4;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    MySQLiteOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new MySQLiteOpenHelper(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        siguienteTarea();
        avisoTarea();
    }

    private void avisoTarea(){



        if(comprobarTareaParaHoy()) {


            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// Sets an ID for the notification, so it can be updated.
            int notifyID = 1;
// The id of the channel.
            String CHANNEL_ID = "my_channel_01";
// Create a notification and set the notification channel.
            Notification notification = new Notification.Builder(MainActivity.this)
                    .setContentTitle("Tarea pendiente")
                    .setContentText("Tienes una tarea que termina hoy.")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setChannelId(CHANNEL_ID)
                    .build();
// Issue the notification.
            mNotificationManager.notify(notifyID, notification);
        }
    }

    private boolean comprobarTareaParaHoy() {

        String fechaTarea;

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String fechaHoy = df.format(c);

        Boolean tareaHoy = false;

        Cursor datos = db.getTareas();

        while(datos.moveToNext()){
            fechaTarea = datos.getString(datos.getColumnIndex("fecha"));

            if (fechaHoy.equals(fechaTarea)){
                tareaHoy = true;
            }
        }
        return  tareaHoy;
    }

    private void siguienteTarea() {
        int id;
        String nombre;
        String fecha;
        String hora;
        Cursor datos = db.getTareas();


        if(datos.moveToFirst()) {
            id = datos.getInt(datos.getColumnIndex("id"));
            nombre = datos.getString(datos.getColumnIndex("nombre"));
            fecha = datos.getString(datos.getColumnIndex("fecha"));
            hora = datos.getString(datos.getColumnIndex("hora"));

            String siguienteTarea = "la siguiente area es: " +
                    nombre + " el dia " + fecha + " a las " + hora;

            Toast.makeText(this, siguienteTarea, Toast.LENGTH_LONG).show();
        }
    }

    public void NuevaTarea (View view) {
        Intent i = new Intent(this, NuevaTareaActivity.class);
        startActivity(i);
    }

    public void ListaTareas (View view) {
        Intent i = new Intent(this, ListaTareasActivity.class);
        startActivity(i);
    }
}
