package com.jmunoz.comicsform.AppComicsFormulario.services;

import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Usuario;
import com.jmunoz.comicsform.AppComicsFormulario.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    @Qualifier("usuario")
    private Repository<Usuario> repository;

    @Override
    public Usuario findUserById(Long id) throws SQLException {
        return repository.findById(id);
    }

    @Override
    public Usuario findUsuarioByUsername(String username) throws SQLException {
        Map<String, String> fieldValueUsername = Map.of("username", username);
        return repository.findByField(fieldValueUsername);
    }

    @Override
    public Usuario findUserByEmail(String email) throws SQLException {
        Map<String, String> fieldValueMail = Map.of("email", email);
        return repository.findByField(fieldValueMail);
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) throws SQLException {
        return repository.save(usuario);
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) throws SQLException {
        return repository.update(usuario);
    }
}
