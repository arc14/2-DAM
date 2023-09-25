package com.ad.model;

import java.sql.Date;
import java.time.LocalDate;

public class Partido {

    private String codEquipo;
    private String codEquipoLocal;
    private String codEquipoVisitante;
    private Date fecha;
    private String jornada;

    public Partido(String codEquipo, String codEquipoLocal, String codEquipoVisitante, Date fecha, String jornada) {
        this.codEquipo = codEquipo;
        this.codEquipoLocal = codEquipoLocal;
        this.codEquipoVisitante = codEquipoVisitante;
        this.fecha = fecha;
        this.jornada = jornada;
    }

    public String getCodEquipo() {
        return codEquipo;
    }

    public String getCodEquipoLocal() {
        return codEquipoLocal;
    }

    public String getCodEquipoVisitante() {
        return codEquipoVisitante;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getJornada() {
        return jornada;
    }

    @Override
    public String toString() {
        return "Partido{" +
                "codEquipo='" + codEquipo + '\'' +
                ", codEquipoLocal='" + codEquipoLocal + '\'' +
                ", codEquipoVisitante='" + codEquipoVisitante + '\'' +
                ", fecha=" + fecha +
                ", jornada='" + jornada + '\'' +
                '}';
    }
}
