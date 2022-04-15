package com.jmunoz.comicsform.AppComicsFormulario.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface Repository<T> {
    List<T> findAll() throws SQLException;
    List<T> findAllByUserId(Long id) throws SQLException;
    T findById(Long id) throws SQLException;
    T findByField(Map<String, String> fieldValue) throws SQLException;
    T save(T t) throws SQLException;
    T update(T t) throws SQLException;
    void delete(Integer id) throws SQLException;
}
