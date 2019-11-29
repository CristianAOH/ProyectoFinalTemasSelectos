package com.itchii.cristian.argame.Clase;

public class Personaje {
    private String Nombre;
    private String Bloqueo;

    public Personaje() {
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getBloqueo() {
        return Bloqueo;
    }

    public void setBloqueo(String bloqueo) {
        Bloqueo = bloqueo;
    }

    @Override
    public String toString() {
        return Bloqueo;
    }
}
