package planificaciondeprocesos;

import java.util.ArrayList;
import java.util.Iterator;

public class main {

    //Sistema de Proceso Fifo
    public static void main(String[] args) {

        ArrayList<Procesos> proceso = new ArrayList<Procesos>();

        proceso.add(new Procesos("P1", 2, 8));
        proceso.add(new Procesos("P2", 1, 4));
        proceso.add(new Procesos("P3", 10, 9));
        proceso.add(new Procesos("P4", 3, 5));
        proceso.add(new Procesos("P5", 9, 2));
        
        fifo(proceso);
    }

    private static void fifo(ArrayList<Procesos> a) {

        System.out.println("Linea de ejecucion");

        int auxCont = 0;

        while (!a.isEmpty()) {
            for (int i = 0; i < a.size(); i++) {
                if (a.get(i).getTiempoEntrada() == auxCont) {

                    for (int k = 0; k < a.get(i).getDuracion(); k++) {
                        System.out.print("[" + a.get(i).getNombreProceso() + "]");
                        
                    }
                    a.remove(i);
                    i = 0;

                }
            }
            auxCont++;
        }

    }
}
