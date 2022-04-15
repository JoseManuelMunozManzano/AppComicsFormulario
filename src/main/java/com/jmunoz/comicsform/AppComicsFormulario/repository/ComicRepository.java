package com.jmunoz.comicsform.AppComicsFormulario.repository;

import com.jmunoz.comicsform.AppComicsFormulario.config.ConexionBaseDatos;
import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Comic;
import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Tematica;
import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Repository("comic")
public class ComicRepository implements Repository<Comic> {
    @Autowired
    private ConexionBaseDatos conexionBaseDatos;

    private Connection conn;

    @Override
    public List<Comic> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<Comic> findAllByUserId(Long id) throws SQLException {
        conn = conexionBaseDatos.getConnection();
        List<Comic> comics = new ArrayList<>();

        try(PreparedStatement stmt = conn.prepareStatement("SELECT c.*, u.username, t.nombre" +
                " FROM comics c" +
                " INNER JOIN usuarios u ON u.id = c.usuario_id" +
                " INNER JOIN tematicas t ON t.id = c.tematica_id" +
                " WHERE c.usuario_id = ?")) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    comics.add(crearComic(rs));
                }
            }
        }

        return comics;
    }

    @Override
    public Comic findById(Long id) throws SQLException {
        return null;
    }

    @Override
    public Comic findByField(Map<String, String> fieldValue) throws SQLException {
        return null;
    }

    @Override
    public Comic save(Comic comic) throws SQLException {
        return null;
    }

    @Override
    public Comic update(Comic comic) throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer id) throws SQLException {

    }

    private Comic crearComic(ResultSet rs) throws SQLException {
        Comic comic = new Comic();

        comic.setId(rs.getLong("id"));
        comic.setNombre(rs.getString("c.nombre"));
        comic.setPrecio(rs.getFloat("precio"));
        comic.setFechaRegistro(rs.getDate("fecha_registro"));

        Tematica tematica = new Tematica();
        tematica.setId(rs.getLong("tematica_id"));
        tematica.setNombre(rs.getString("t.nombre"));
        comic.setTematica(tematica);

        Usuario usuario = new Usuario();
        usuario.setId(rs.getLong("usuario_id"));
        usuario.setUsername(rs.getString("username"));
        comic.setUsuario(usuario);

        return comic;
    }
}
