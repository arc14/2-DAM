package com.ad.model;

public class Equipo {

    private String codEquipo;
    private String nombre;
    private String localidad;

    public String getCodEquipo() {
        return codEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public Equipo(String codEquipo, String nombre, String localidad) {
        this.codEquipo = codEquipo;
        this.nombre = nombre;
        this.localidad = localidad;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "codEquipo='" + codEquipo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", localidad='" + localidad + '\'' +
                '}';
    }
}
