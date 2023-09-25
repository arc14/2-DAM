package com.ad.vista;

import com.ad.daoImpl.DAOEquipoImpl;
import com.ad.daoImpl.DAOIncidenciaImpl;
import com.ad.model.Incidencia;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class IncidenciasApp {
    public static void main(String[] args) {

        DAOIncidenciaImpl daoIncidencia = new DAOIncidenciaImpl();
        DAOEquipoImpl daoEquipo = new DAOEquipoImpl();
        File f = new File("res/equipos.txt");

        System.out.println("*** Sistema de incidencias de La Liga ***");

        Scanner sc = new Scanner(System.in);
        int opMenu;

        do {
            //Interfaz Menu

            System.out.println("""
                    Selecciona una opcion:\s
                    1. Insertar incidencia (dado el número de partido)
                    2. Modificar (dado el nº de incidencia)
                    3. Eliminar incidencia (dado el n º de incidencia).
                    4. Mostrar informe de liga
                    5. Importar equipos
                    6. Salir""");

            opMenu = sc.nextInt();


            switch (opMenu) {
                case 1:
                    List<Incidencia> listadoInc = daoIncidencia.listarIncidencias();
                    for (Incidencia i: listadoInc) {
                        System.out.println(i.toString());
                    }

                    System.out.println("Dime el numero de incidencia");
                    String nIn = sc.next();

                    System.out.println("Dime el codigo del partido");
                    String cPar = sc.next();

                    System.out.println("Dime el codigo de jugador");
                    String cJug = sc.next();

                    System.out.println("Dime el minuto en el que ha ocurrido la incidencia");
                    int min = sc.nextInt();

                    System.out.println("Dime el tipo de incidencia (Gol, Tarjeta Amarilla o Tarjeta Roja)");
                    String tipoInc = sc.next();

                    Incidencia incRegistro = new Incidencia(nIn, cPar, cJug, min, tipoInc);

                    if (daoIncidencia.insert(incRegistro)){
                        System.out.println("Registro añadido");
                    }

                    break;
                case 2:
                    List<Incidencia> listadoInc2 = daoIncidencia.listarIncidencias();
                    for (Incidencia i: listadoInc2) {
                        System.out.println(i.toString());
                    }

                    System.out.println("Dime el numero de incidencia a editar");
                    String nIn2 = sc.next();

                    System.out.println("Dime el codigo del partido a editar");
                    String cPar2 = sc.next();

                    System.out.println("Dime el codigo de jugador a editar");
                    String cJug2 = sc.next();

                    System.out.println("Dime el minuto de partido a editar");
                    int min2 = sc.nextInt();

                    System.out.println("Dime el tipo de incidencia a editar (Gol, Tarjeta_amarilla o Tarjeta_roja)");
                    String tipoInc2 = sc.next();

                    Incidencia incRegistro2 = new Incidencia(nIn2, cPar2, cJug2, min2, tipoInc2);

                    if (daoIncidencia.update(incRegistro2)){
                        System.out.println("Registro añadido");
                    }

                    break;
                case 3:

                    List<Incidencia> listadoInc3 = daoIncidencia.listarIncidencias();
                    for (Incidencia i: listadoInc3) {
                        System.out.println(i.toString());
                    }

                    System.out.println("¿Que incidencia quieres borrar?");
                    String nIncDel = sc.next();


                    if (daoIncidencia.delete(nIncDel)){
                        System.out.println("Registro Borrado");
                    }
                    break;
                case 4:
                    //TODO
                    break;
                case 5:
                    if(daoEquipo.importarEquipo(f)){
                        System.out.println("El archivo se ha leido y la inserción masiva de datos se realizó correctamente.");
                    }
                    break;
                case 6:
                    System.out.println("Operacion acabadas");
                    break;
                default:
                    System.out.println("Opción no reconocida");
                    break;
            }
        } while(opMenu != 6 );
    }
}