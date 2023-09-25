package com.ad.model;

public class Incidencia {

    private String numIncidencia;
    private String codPartido;
    private String codJugador;
    private int minuto;
    private String tipo;

    public Incidencia(String numIncidencia, String codPartido, String codJugador, int minuto, String tipo) {
        this.numIncidencia = numIncidencia;
        this.codPartido = codPartido;
        this.codJugador = codJugador;
        this.minuto = minuto;
        this.tipo = tipo;
    }

    public String getNumIncidencia() {
        return numIncidencia;
    }

    public void setNumIncidencia(String numIncidencia) {
        this.numIncidencia = numIncidencia;
    }

    public String getCodPartido() {
        return codPartido;
    }

    public void setCodPartido(String codPartido) {
        this.codPartido = codPartido;
    }

    public String getCodJugador() {
        return codJugador;
    }

    public void setCodJugador(String codJugador) {
        this.codJugador = codJugador;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Incidencia: " + numIncidencia + '\'' +
                ", codPartido:'" + codPartido + '\'' +
                ", codJugador:'" + codJugador + '\'' +
                ", minuto:" + minuto + '\'' +
                ", tipo:'" + tipo + '\'';
    }
}
