package com.jmunoz.comicsform.AppComicsFormulario.repository;

import com.jmunoz.comicsform.AppComicsFormulario.config.ConexionBaseDatos;
import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Tematica;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Repository("tematica")
public class TematicaRepository implements Repository<Tematica> {
    @Autowired
    private ConexionBaseDatos conexionBaseDatos;

    private Connection conn;
    @Override
    public List<Tematica> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<Tematica> findAllByUserId(Long id) throws SQLException {
        return null;
    }

    @Override
    public Tematica findById(Long id) throws SQLException {
        return null;
    }

    @Override
    public Tematica findByField(Map<String, String> fieldValue) throws SQLException {
        return null;
    }

    @Override
    public Tematica save(Tematica tematica) throws SQLException {
        return null;
    }

    @Override
    public Tematica update(Tematica tematica) throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer id) throws SQLException {

    }
}
