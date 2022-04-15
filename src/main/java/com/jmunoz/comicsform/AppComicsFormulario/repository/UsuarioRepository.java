package com.jmunoz.comicsform.AppComicsFormulario.repository;

import com.jmunoz.comicsform.AppComicsFormulario.config.ConexionBaseDatos;
import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Repository("usuario")
public class UsuarioRepository implements Repository<Usuario> {
    @Autowired
    private ConexionBaseDatos conexionBaseDatos;

    private Connection conn;

    @Override
    public List<Usuario> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<Usuario> findAllByUserId(Long id) throws SQLException {
        return null;
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
        return null;
    }

    @Override
    public Usuario update(Usuario usuario) throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer id) throws SQLException {

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
