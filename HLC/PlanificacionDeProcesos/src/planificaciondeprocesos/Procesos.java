package planificaciondeprocesos;

public class Procesos {

    final private String nombreProceso;
    final private int tiempoEntrada;
    final private int duracion;

    public Procesos(String nombreProceso, int tiempoEntrada, int duracion) {
        this.nombreProceso = nombreProceso;
        this.tiempoEntrada = tiempoEntrada;
        this.duracion = duracion;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getTiempoEntrada() {
        return tiempoEntrada;
    }

    public String getNombreProceso() {
        return nombreProceso;
    }

    @Override
    public String toString() {
        return "Proceso{" + "nombreProceso=" + nombreProceso + ", tiempoEntrada=" + tiempoEntrada + ", duracion=" + duracion + '}';
    }
}
