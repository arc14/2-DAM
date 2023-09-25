package iesm.pmdm.t4_agenda;

public class Contacto implements Comparable<Contacto>{
    private String name;
    private Integer tlfn;
    private String email;

    public Contacto(String nombre, Integer telefono, String email) {
        this.name = nombre;
        this.tlfn = telefono;
        this.email = email;
    }

    public String getNombre() {
        return name;
    }

    public void setNombre(String nombre) {
        this.name = nombre;
    }

    public Integer getTelefono() {
        return tlfn;
    }

    public void setTelefono(Integer telefono) {
        this.tlfn = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "nombre='" + name + '\'' +
                ", telefono=" + tlfn +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public int compareTo(Contacto contacto) {
        return name.compareTo(contacto.getNombre());
    }
}
