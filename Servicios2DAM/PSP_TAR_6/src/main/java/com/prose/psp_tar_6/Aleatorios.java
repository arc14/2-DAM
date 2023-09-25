package com.prose.psp_tar_6;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Aleatorios {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String newNum;

        try {
            do {

                Process p = Aleatorios.exec(GeneradorAleatorios.class);

                int salida = p.waitFor();

                System.out.println("Valor de ejecucion: " + salida);

                if (mostrarResultadoBuffer(p) == salida) {
                    System.out.println("El valor de salida y el valor del metodo aleatorio son iguales");
                } else {
                    System.out.println("Ambos valores no son iguales");
                }

                System.out.println("Calcular nuevo numero aleatorio? (s/n)");
                newNum = sc.nextLine();

            } while (newNum.equalsIgnoreCase("s"));

            System.out.println("Programa finalizado");

        } catch (IOException | InterruptedException e) {
            System.out.println("-1");
        }

    }

    public static Process exec(Class clase) throws IOException {
        String javaHome = System.getProperty("java.home");
        String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
        String classpath = System.getProperty("java.class.path");
        String className = clase.getCanonicalName();

        ProcessBuilder builder = new ProcessBuilder(javaBin, "-cp", classpath, className);
        return builder.start();
    }

    public static Process exec(Class clase, String[] args) throws IOException {
        String javaHome = System.getProperty("java.home");
        String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
        String classpath = System.getProperty("java.class.path");
        String className = clase.getCanonicalName();

        ProcessBuilder builder = new ProcessBuilder(javaBin, "-cp", classpath, className);

        for (String arg : args) {
            builder.command().add(arg);
        }

        return builder.start();
    }

    public static int mostrarResultadoBuffer(Process p) {
        // Creamos el flujo de lectura con el proceso
        BufferedReader leer = new BufferedReader(new InputStreamReader(p.getInputStream()));

        try {
            String linea = leer.readLine();
            int res = Integer.valueOf(linea);
            
            if (-1 < res && res <= 10) {
                System.out.println("Valor de salida: " + linea);
                return res;
            } else {
                System.out.println("Error valor de salida");
                return -1;
            }
        } catch (IOException e) {
            // Controlamos el error por si hay error en el flujo de lectura
            System.out.println("-1");
            return -1;
            // e.printStackTrace();
        }
    }
}
