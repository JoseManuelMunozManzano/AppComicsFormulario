package com.jmunoz.comicsform.AppComicsFormulario.models.domain;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UsuarioSeleccionado {
    private Long id;

    public UsuarioSeleccionado() {
    }

    public UsuarioSeleccionado(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
