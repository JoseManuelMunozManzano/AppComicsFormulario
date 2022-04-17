package com.jmunoz.comicsform.AppComicsFormulario.editors;

import com.jmunoz.comicsform.AppComicsFormulario.services.TematicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.sql.SQLException;

@Component
public class TematicaPropertyEditor extends PropertyEditorSupport {

    @Autowired
    private TematicaService service;

    @Override
    public void setAsText(String idString) throws IllegalArgumentException {
        try {
            Long id = Long.parseLong(idString);
            this.setValue(service.findTematicaById(id));
        } catch (NumberFormatException | SQLException e) {
            setValue(null);
        }
    }
}
