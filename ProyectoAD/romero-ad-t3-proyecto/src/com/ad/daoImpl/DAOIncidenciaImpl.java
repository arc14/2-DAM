package com.ad.daoImpl;

import com.ad.dao.DAOIncidencia;
import com.ad.model.Incidencia;
import com.ad.utils.Conexion;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOIncidenciaImpl implements DAOIncidencia {

    Connection con;

    @Override
    public List<Incidencia> listarIncidencias() {
        List<Incidencia> listaIncidencias = new ArrayList<>();

        String sql = "SELECT * FROM INCIDENCIA";

        try {
            con = Conexion.getConnection();
            con.setAutoCommit(false);

            Statement sqlStatement = con.createStatement();
            ResultSet sel = sqlStatement.executeQuery(sql);

            while (sel.next()) {
                Incidencia inci = new Incidencia(sel.getString("NumIncidencia")
                        , sel.getString("CodPartido")
                        , sel.getString("CodJugador")
                        , sel.getInt("Minuto")
                        , sel.getString("Tipo"));

                inci.toString();
                listaIncidencias.add(inci);
            }

        } catch (SQLException | IOException e) {
            System.err.print("Transaction is being rolled back");
            listaIncidencias = null;
            try {
                if (con != null)
                    con.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        return listaIncidencias;
    }

    @Override
    public boolean insert(Incidencia incidencia) {

        boolean resIns;

        try{

            String sql = "INSERT INTO INCIDENCIA (NumIncidencia, CodPartido, CodJugador, Minuto, Tipo) values(?,?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            //Add Parameters
            stmt.setString(1, incidencia.getNumIncidencia());
            stmt.setString(2, incidencia.getCodPartido());
            stmt.setString(3, incidencia.getCodJugador());
            stmt.setInt(4, incidencia.getMinuto());
            stmt.setString(5, incidencia.getTipo());

            stmt.executeUpdate();

            con.commit();
            con.close();

            resIns = true;

        } catch (SQLException e) {
            System.err.print("Transaction is being rolled back");
            resIns = false;
            try {
                System.out.println(e.getMessage());
                    con.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }

        return resIns;
    }

    @Override
    public boolean update(Incidencia incidencia) {

        boolean resIns;

        try {
            String sql = "UPDATE INCIDENCIA SET CodPartido=?, CodJugador=?, Minuto=?, Tipo=? WHERE NumIncidencia=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            //Add Parameters
            stmt.setString(1, incidencia.getCodPartido());
            stmt.setString(2, incidencia.getCodJugador());
            stmt.setInt(3, incidencia.getMinuto());
            stmt.setString(4, incidencia.getTipo());
            stmt.setString(5,incidencia.getNumIncidencia());

            stmt.executeUpdate();

            con.commit();
            con.close();

            resIns = true;

        } catch (SQLException e) {
            System.err.print("Transaction is being rolled back");
            resIns = false;
            try {
                if (con != null)
                    System.out.println(e.getMessage());
                    con.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }

        return resIns;
    }

    @Override
    public boolean delete(String numInc) {

        boolean valueReturn = false;


        String sql = "DELETE FROM INCIDENCIA WHERE NumIncidencia=?";

        try {
            con = Conexion.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,numInc);

            stmt.executeUpdate();

            con.commit();
            con.close();

            valueReturn = true;

        } catch (SQLException | IOException e) {
            System.err.print("Transaction is being rolled back");
            valueReturn = false;
            try {
                if (con != null)
                    System.out.println(e.getMessage());
                con.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        return valueReturn;
    }
}
