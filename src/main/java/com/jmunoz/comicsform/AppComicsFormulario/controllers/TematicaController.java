package com.jmunoz.comicsform.AppComicsFormulario.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("tematica")
public class TematicaController {

    @GetMapping({"/", ""})
    public String ver(Model model) {
        model.addAttribute("titulo", "Temáticas");

        return "tematica/tematica";
    }

    @GetMapping("alta")
    public String alta(Model model) {
        model.addAttribute("titulo", "Alta de Temática");

        return "tematica/alta";
    }

    @GetMapping("editar")
    public String editar(Model model) {
        model.addAttribute("titulo", "Editar Temática");

        return "tematica/editar";
    }

    @GetMapping("borrar")
    public String borrar(Model model) {
        model.addAttribute("titulo", "Borrar Temática");

        return "tematica/borrar";
    }
}
