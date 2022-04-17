package com.jmunoz.comicsform.AppComicsFormulario.repository;

import com.jmunoz.comicsform.AppComicsFormulario.config.ConexionBaseDatos;
import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Comic;
import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Tematica;
import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
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
        conn = conexionBaseDatos.getConnection();
        List<Comic> comics = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT c.*, u.username, t.nombre" +
                     " FROM comics c" +
                     " INNER JOIN usuarios u ON u.id = c.usuario_id" +
                     " INNER JOIN tematicas t ON t.id = c.tematica_id")) {
            while (rs.next()) {
                comics.add(crearComic(rs));
            }
        }

        return comics;
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
                while (rs.next()) {
                    comics.add(crearComic(rs));
                }
            }
        }

        return comics;
    }

    @Override
    public Comic findById(Long id) throws SQLException {
        conn = conexionBaseDatos.getConnection();
        Comic comic = null;

        try (PreparedStatement stmt = conn.prepareStatement("SELECT c.*, u.username, t.nombre" +
                " FROM comics c" +
                " INNER JOIN usuarios u ON u.id = c.usuario_id" +
                " INNER JOIN tematicas t ON t.id = c.tematica_id" +
                " WHERE c.id = ?")) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    comic = crearComic(rs);
                }
            }
        }

        return comic;
    }

    @Override
    public Comic findByField(Map<String, String> fieldValue) throws SQLException {
        conn = conexionBaseDatos.getConnection();
        Comic comic = null;
        String sql = "SELECT * FROM comics WHERE 1 = 1";
        int index = 1;

        for (String key : fieldValue.keySet()) {
            sql = sql.concat(" AND ").concat(key).concat(" = ?");
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (String value : fieldValue.values()) {
                stmt.setString(index++, value);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    comic = crearComic(rs);
                }
            }
        }

        return comic;
    }

    @Override
    public Comic save(Comic comic) throws SQLException {
        conn = conexionBaseDatos.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO comics (nombre, precio, fecha_registro, tematica_id, usuario_id) " +
                "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, comic.getNombre());
            stmt.setFloat(2, comic.getPrecio());
            stmt.setDate(3, new Date(comic.getFechaRegistro().getTime()));
            stmt.setLong(4, comic.getTematica().getId());
            stmt.setLong(5, comic.getUsuario().getId());

            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    comic.setId(rs.getLong(1));
                }
            }
        }

        return comic;
    }

    @Override
    public Comic update(Comic comic) throws SQLException {
        conn = conexionBaseDatos.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement("UPDATE comics SET nombre = ?, precio = ?, " +
                "fecha_registro = ?, tematica_id = ?, usuario_id = ? WHERE id = ?")) {
            stmt.setString(1, comic.getNombre());
            stmt.setFloat(2, comic.getPrecio());
            stmt.setDate(3, new Date(comic.getFechaRegistro().getTime()));
            stmt.setLong(4, comic.getTematica().getId());
            stmt.setLong(5, comic.getUsuario().getId());
            stmt.setLong(6, comic.getId());

            stmt.executeUpdate();
        }

        return comic;
    }

    @Override
    public void delete(Long id) throws SQLException {
        conn = conexionBaseDatos.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM comics WHERE id = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
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
