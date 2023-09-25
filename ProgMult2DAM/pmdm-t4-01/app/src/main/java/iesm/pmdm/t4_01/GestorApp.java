package iesm.pmdm.t4_01;

public interface GestorApp {
    public void cargarWeb(String url);
    public void abrirMarcadorLlamada();
    public void marcarLlamada(String telefono);
    public void realizarLlamada(String telefono);
    public void mandarSms(String contenido);
    public void mandarSms(String telefono, String contenido);
}
