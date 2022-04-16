package com.jmunoz.comicsform.AppComicsFormulario.services;

import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Usuario;

import java.sql.SQLException;

public interface UsuarioService {
    Usuario findUserById(Long id) throws SQLException;
    Usuario findUsuarioByUsername(String username) throws SQLException;
    Usuario findUserByEmail(String email) throws SQLException;
    Usuario saveUsuario(Usuario usuario) throws SQLException;
    Usuario updateUsuario(Usuario usuario) throws SQLException;
}
