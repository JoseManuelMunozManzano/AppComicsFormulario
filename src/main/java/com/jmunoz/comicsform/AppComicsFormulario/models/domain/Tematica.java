package com.jmunoz.comicsform.AppComicsFormulario.models.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Tematica {
    private Long id;
    @NotBlank
    @Size(min = 1, max=45)
    private String nombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.id.toString();
    }
}
