package com.ad.model;

import java.sql.Date;
import java.time.LocalDate;

public class Jugador {
    private String codJugador;
    private String nombre;
    private Date fechaNac;
    private String demarcacion;
    private String codEquipo;

    public Jugador(String codJugador, String nombre, Date fechaNac, String demarcacion, String codEquipo) {
        this.codJugador = codJugador;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.demarcacion = demarcacion;
        this.codEquipo = codEquipo;
    }

    public String getCodJugador() {
        return codJugador;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public String getDemarcacion() {
        return demarcacion;
    }

    public String getCodEquipo() {
        return codEquipo;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "codJugador='" + codJugador + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaNac=" + fechaNac +
                ", demarcacion='" + demarcacion + '\'' +
                ", codEquipo='" + codEquipo + '\'' +
                '}';
    }
}
