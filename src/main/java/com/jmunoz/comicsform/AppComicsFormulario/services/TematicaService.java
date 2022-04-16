package com.jmunoz.comicsform.AppComicsFormulario.services;

import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Tematica;

import java.sql.SQLException;
import java.util.List;

public interface TematicaService {
    List<Tematica> getTematicas() throws SQLException;
    Tematica findComicsByTematica(Long id) throws SQLException;
    Tematica saveTematica(Tematica tematica) throws SQLException;
    void deleteTematica(Long id) throws SQLException;
}
