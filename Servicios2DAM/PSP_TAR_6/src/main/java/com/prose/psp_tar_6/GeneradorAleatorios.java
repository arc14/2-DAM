package com.prose.psp_tar_6;

import java.util.Random;

public class GeneradorAleatorios {

    public static void main(String[] args) {

        int a = aleatorio(0, 10);

        //Salida Estandar
        System.out.println(a);

        //salida proceso
        System.exit(a);

    }

    public static int aleatorio(int min, int max) {
        Random r = new Random();
        return min + r.nextInt(max - min + 1);
    }
}
