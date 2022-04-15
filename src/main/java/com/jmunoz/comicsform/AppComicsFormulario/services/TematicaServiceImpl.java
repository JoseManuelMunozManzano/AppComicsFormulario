package com.jmunoz.comicsform.AppComicsFormulario.services;

import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Tematica;
import com.jmunoz.comicsform.AppComicsFormulario.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TematicaServiceImpl implements TematicaService {
    @Autowired
    @Qualifier("tematica")
    private Repository<Tematica> repository;

    @Override
    public List<Tematica> getTematicas() throws SQLException {

        return repository.findAll();
    }

    @Override
    public Tematica findComicsByTematica(Long id) throws SQLException {

        return repository.findById(id);
    }

    @Override
    public Tematica saveTematica(Tematica tematica) throws SQLException {

        return repository.save(tematica);
    }

    @Override
    public void deleteTematica(Integer id) throws SQLException {
        repository.delete(id);
    }
}
