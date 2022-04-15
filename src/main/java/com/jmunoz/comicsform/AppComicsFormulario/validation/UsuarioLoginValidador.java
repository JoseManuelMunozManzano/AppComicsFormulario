package com.jmunoz.comicsform.AppComicsFormulario.validation;

import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Usuario;
import com.jmunoz.comicsform.AppComicsFormulario.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.sql.SQLException;

@Component
public class UsuarioLoginValidador implements Validator {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Usuario.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Usuario usuario = (Usuario) target;

        Usuario usuarioBD;

        try {
            usuarioBD = usuarioService.findUsuarioByUsername(usuario.getUsername());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (usuarioBD == null || !usuarioBD.getPassword().equals(usuario.getPassword())) {
            errors.rejectValue("username", "usuario.o.contrasena.erronea");
        }
    }
}
