package com.jmunoz.comicsform.AppComicsFormulario.services;

import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Comic;
import com.jmunoz.comicsform.AppComicsFormulario.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ComicServiceImpl implements ComicService {
    @Autowired
    @Qualifier("comic")
    private Repository<Comic> repository;

    @Override
    public List<Comic> findComicsByUserId(Long userId) throws SQLException {
        return repository.findAllByUserId(userId);
    }

    @Override
    public Comic findComicById(Long id) throws SQLException {
        return repository.findById(id);
    }

    @Override
    public Comic saveComic(Comic comic) throws SQLException {
        return repository.save(comic);
    }

    @Override
    public Comic updateComic(Comic comic) throws SQLException {
        return repository.update(comic);
    }

    @Override
    public void deleteComic(Integer id) throws SQLException {
        repository.delete(id);
    }
}
