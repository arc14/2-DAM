/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.padrehijo;

/**
 *
 * @author losad
 */
public class Hijo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Soy el hijo y me duermo tres segundos ...");
        Thread.sleep(3000);

        // Finalización del proceso con valor de salida
        System.exit(50);
    }
}
