package com.jmunoz.comicsform.AppComicsFormulario.services;

import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Comic;

import java.sql.SQLException;
import java.util.List;

public interface ComicService {
    List<Comic> findComicsByUserId(Long userId) throws SQLException;
    Comic findComicById(Long id) throws SQLException;
    Comic saveComic(Comic comic) throws SQLException;
    Comic updateComic(Comic comic) throws SQLException;
    void deleteComic(Long id) throws SQLException;
}
