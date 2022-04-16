package com.jmunoz.comicsform.AppComicsFormulario.repository;

import com.jmunoz.comicsform.AppComicsFormulario.config.ConexionBaseDatos;
import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Repository("usuario")
public class UsuarioRepository implements Repository<Usuario> {
    @Autowired
    private ConexionBaseDatos conexionBaseDatos;

    private Connection conn;

    @Override
    public List<Usuario> findAll() throws SQLException {
        conn = conexionBaseDatos.getConnection();
        List<Usuario> usuarios = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios")) {

            while (rs.next()) {
                usuarios.add(crearUsuario(rs));
            }
        }

        return usuarios;
    }

    @Override
    public List<Usuario> findAllByUserId(Long id) throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = findById(id);

        if (usuario != null) {
            usuarios.add(usuario);
        }

        return usuarios;
    }

    @Override
    public Usuario findById(Long id) throws SQLException {
        conn = conexionBaseDatos.getConnection();
        Usuario usuario = null;

        try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarios WHERE id = ?")) {
            stmt.setLong(1, id);

            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = crearUsuario(rs);
                }
            }
        }

        return usuario;
    }

    @Override
    public Usuario findByField(Map<String, String> fieldValue) throws SQLException {
        conn = conexionBaseDatos.getConnection();
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE 1 = 1";
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
                    usuario = crearUsuario(rs);
                }
            }
        }

        return usuario;
    }

    @Override
    public Usuario save(Usuario usuario) throws SQLException {
        conn = conexionBaseDatos.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO usuarios (username, password, email)" +
                " VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getEmail());

            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setId(rs.getLong(1));
                }
            }
        }

        return usuario;
    }

    @Override
    public Usuario update(Usuario usuario) throws SQLException {
        conn = conexionBaseDatos.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement("UPDATE usuarios SET username = ?, password = ?, email = ? WHERE id = ?")) {
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getEmail());
            stmt.setLong(4, usuario.getId());

            stmt.executeUpdate();
        }

        return usuario;
    }

    @Override
    public void delete(Long id) throws SQLException {
        conn = conexionBaseDatos.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM usuarios WHERE id = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Usuario crearUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getLong("id"));
        usuario.setUsername(rs.getString("username"));
        usuario.setPassword(rs.getString("password"));
        usuario.setEmail(rs.getString("email"));

        return usuario;
    }
}
