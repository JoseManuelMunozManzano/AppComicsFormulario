package com.jmunoz.comicsform.AppComicsFormulario.controllers;

import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Tematica;
import com.jmunoz.comicsform.AppComicsFormulario.services.TematicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@Controller
@SessionAttributes("tematica")
@RequestMapping("tematica")
public class TematicaController {

    @Autowired
    public TematicaService tematicaService;

    @GetMapping({"/", ""})
    public String ver(Model model) throws SQLException {
        model.addAttribute("titulo", "Temáticas");

        List<Tematica> tematicas = tematicaService.getTematicas();
        model.addAttribute("tematicas", tematicas);

        return "tematica/tematica";
    }

    @GetMapping("/alta")
    public String alta(Model model) {
        model.addAttribute("titulo", "Alta de Temática");

        Tematica tematica = new Tematica();
        model.addAttribute("tematica", tematica);

        return "tematica/alta";
    }

    @PostMapping("/alta")
    public String alta(@Valid Tematica tematica, BindingResult result, Model model) throws SQLException {
        model.addAttribute("titulo", "Alta de Temática");

        if (result.hasErrors()) {
            return "tematica/alta";
        }

        Tematica tematicaBD = tematicaService.saveTematica(tematica);

        return "redirect:/tematica";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) throws SQLException {
        model.addAttribute("titulo", "Editar Temática");

        Tematica tematica = tematicaService.findTematicaById(id);
        model.addAttribute("tematica", tematica);

        return "tematica/editar";
    }

    @PostMapping("/editar/{id}")
    public String editar(@Valid Tematica tematica, BindingResult result, @PathVariable Long id, Model model, SessionStatus status) throws SQLException {
        model.addAttribute("titulo", "Editar Temática");

        if (result.hasErrors()) {
            return "tematica/editar";
        }

        Tematica tematicaBD = tematicaService.updateTematica(tematica);

        status.setComplete();

        return "redirect:/tematica";
    }

    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable Long id, Model model) throws SQLException {
        model.addAttribute("titulo", "Eliminar Temática");

        Tematica tematica = tematicaService.findTematicaById(id);
        model.addAttribute("tematica", tematica);

        return "tematica/borrar";
    }

    @PostMapping("/borrar/{id}")
    public String borrar(Tematica tematica, Model model, SessionStatus status) throws SQLException {
        model.addAttribute("titulo", "Eliminar Temática");

        tematicaService.deleteTematica(tematica.getId());

        status.setComplete();

        return "redirect:/tematica";
    }
}
