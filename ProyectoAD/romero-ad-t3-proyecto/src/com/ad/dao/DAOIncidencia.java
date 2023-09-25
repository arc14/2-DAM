package com.ad.dao;

import com.ad.model.Incidencia;

import java.util.List;

public interface DAOIncidencia {

    List<Incidencia> listarIncidencias();

    boolean insert(Incidencia incidencia);

    boolean update(Incidencia incidencia);

    boolean delete(String numInc);

}
