package com.ad.daoImpl;

import com.ad.dao.DAOEquipo;
import com.ad.model.Equipo;
import com.ad.model.Incidencia;
import com.ad.utils.Conexion;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DAOEquipoImpl implements DAOEquipo {
    @Override
    public boolean importarEquipo(File archivo) {
        Connection con = null;
        boolean a;
        ArrayList<Equipo> listaEquipo = new ArrayList<>();

        try {
            // Existencia y procesamiento del fichero
            if (archivo.exists()) {
                BufferedReader in = new BufferedReader(new FileReader(archivo));
                String leido;

                // Recorrido del fichero
                while ((leido = in.readLine()) != null) {
                    String[] array = leido.split(",");
                    listaEquipo.add(new Equipo(array[0], array[1], array[2]));

                    // implementar código
                    try {

                        con = Conexion.getConnection();
                        con.setAutoCommit(false);

                        String sql = "INSERT INTO EQUIPO (CodEquipo,Nombre,Localidad) values(?,?,?)";

                        PreparedStatement stmt = con.prepareStatement(sql);
                        //Add Parameters
                        stmt.setString(1, array[0]);
                        stmt.setString(2, array[1]);
                        stmt.setString(3, array[2]);


                        stmt.executeUpdate();


                    } catch (SQLException | IOException e) {
                        System.err.print("Transaction is being rolled back");
                        try {
                            if (con != null)
                                con.rollback();

                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        throw new RuntimeException(e);
                    }

                }
                in.close();
                try {
                    con.commit();
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


            } else {
                System.out.println("El fichero " + archivo.getName() + " no existe");
            }
            a = true;
        } catch (IOException e) {
            a = false;
            throw new RuntimeException(e);

        }

        return a;
    }
}
