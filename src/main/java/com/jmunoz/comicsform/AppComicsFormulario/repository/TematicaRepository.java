package com.jmunoz.comicsform.AppComicsFormulario.repository;

import com.jmunoz.comicsform.AppComicsFormulario.config.ConexionBaseDatos;
import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Tematica;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Repository("tematica")
public class TematicaRepository implements Repository<Tematica> {
    @Autowired
    private ConexionBaseDatos conexionBaseDatos;

    private Connection conn;

    @Override
    public List<Tematica> findAll() throws SQLException {
        conn = conexionBaseDatos.getConnection();
        List<Tematica> tematicas = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tematicas")) {

            while (rs.next()) {
                tematicas.add(crearTematica(rs));
            }
        }

        return tematicas;
    }

    @Override
    public List<Tematica> findAllByUserId(Long id) throws SQLException {
        // No action
        return null;
    }

    @Override
    public Tematica findById(Long id) throws SQLException {
        conn = conexionBaseDatos.getConnection();
        Tematica tematica = null;

        try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tematicas WHERE id = ?")) {
            stmt.setLong(1, id);

            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tematica = crearTematica(rs);
                }
            }
        }

        return tematica;
    }

    @Override
    public Tematica findByField(Map<String, String> fieldValue) throws SQLException {
        conn = conexionBaseDatos.getConnection();
        Tematica tematica = null;
        String sql = "SELECT * FROM tematicas WHERE 1 = 1";
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
                    tematica = crearTematica(rs);
                }
            }
        }

        return tematica;
    }

    @Override
    public Tematica save(Tematica tematica) throws SQLException {
        conn = conexionBaseDatos.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO tematicas (nombre) VALUES(?)",
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, tematica.getNombre());

            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    tematica.setId(rs.getLong(1));
                }
            }
        }

        return tematica;
    }

    @Override
    public Tematica update(Tematica tematica) throws SQLException {
        conn = conexionBaseDatos.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement("UPDATE tematicas SET nombre = ? WHERE id = ?")) {
            stmt.setString(1, tematica.getNombre());
            stmt.setLong(2, tematica.getId());
            stmt.executeUpdate();
        }

        return tematica;
    }

    @Override
    public void delete(Long id) throws SQLException {
        conn = conexionBaseDatos.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM tematicas WHERE id = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Tematica crearTematica(ResultSet rs) throws SQLException {
        Tematica t = new Tematica();
        t.setId(rs.getLong("id"));
        t.setNombre(rs.getString("nombre"));

        return t;
    }
}
