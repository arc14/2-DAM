package iesm.pmdm.t4_recyclerview;

public class CartaModel {
    private String nombre, expansion;
    private int imgCarta;

    public CartaModel() {
    }

    public CartaModel(String nombre, String expansion, int imgCarta) {
        this.nombre = nombre;
        this.expansion = expansion;
        this.imgCarta = imgCarta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getExpansion() {
        return expansion;
    }

    public void setExpansion(String expansion) {
        this.expansion = expansion;
    }

    public int getImgCarta() {
        return imgCarta;
    }

    public void setImgCarta(int imgCarta) {
        this.imgCarta = imgCarta;
    }
}
