package com.example.joseantonio.apptareas4;

public class Tarea {
     private String nombre, fecha, hora;
     private int id;

     public Tarea(){

     }

     public  Tarea(String _nombre, String _fecha, String _hora){
         this.nombre = _nombre;
         this.fecha = _fecha;
         this.hora = _hora;
     }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString(){
         return new String(nombre + "  ||  " + fecha + "  ||  " + hora);
    }
}
