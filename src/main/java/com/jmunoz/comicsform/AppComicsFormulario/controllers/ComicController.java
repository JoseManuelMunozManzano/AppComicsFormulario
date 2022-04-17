package com.jmunoz.comicsform.AppComicsFormulario.controllers;

import com.jmunoz.comicsform.AppComicsFormulario.editors.TematicaPropertyEditor;
import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Comic;
import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Tematica;
import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Usuario;
import com.jmunoz.comicsform.AppComicsFormulario.models.domain.UsuarioSeleccionado;
import com.jmunoz.comicsform.AppComicsFormulario.services.ComicService;
import com.jmunoz.comicsform.AppComicsFormulario.services.TematicaService;
import com.jmunoz.comicsform.AppComicsFormulario.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@Controller
@SessionAttributes("comic")
@RequestMapping("comic")
public class ComicController {

    @Autowired
    private ComicService comicService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioSeleccionado usuarioSeleccionado;

    @Autowired
    private TematicaService tematicaService;

    @Autowired
    private TematicaPropertyEditor tematicaEditor;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Tematica.class, tematicaEditor);
    }

    @ModelAttribute("tematicas")
    public List<Tematica> tematicas() throws SQLException {
        return tematicaService.getTematicas();
    }

    @GetMapping("")
    public String ver(Model model) throws SQLException {
        Usuario usuarioBD = usuarioService.findUserById(usuarioSeleccionado.getId());

        model.addAttribute("titulo", "COMICS DE " + usuarioBD.getUsername().toUpperCase());

        List<Comic> comics = comicService.findComicsByUserId(usuarioBD.getId());

        model.addAttribute("comics", comics);
        return "comic/comic";
    }

    @GetMapping("/alta")
    public String alta(Model model) {
        model.addAttribute("titulo", "Alta de Comic");

        Comic comic = new Comic();
        model.addAttribute("comic", comic);

        return "comic/alta";
    }

    @PostMapping("/alta")
    public String alta(@Valid Comic comic, BindingResult result, Model model) throws SQLException {
        model.addAttribute("titulo", "Alta de Comic");

        if (result.hasErrors()) {
            return "comic/alta";
        }

        comic.setUsuario(usuarioService.findUserById(usuarioSeleccionado.getId()));
        Comic comicBD = comicService.saveComic(comic);

        return "redirect:/comic";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) throws SQLException {
        model.addAttribute("titulo", "Editar Comic");

        Comic comic = comicService.findComicById(id);
        model.addAttribute("comic", comic);

        return "comic/editar";
    }

    @PostMapping("/editar/{id}")
    public String editar(@Valid Comic comic, BindingResult result, @PathVariable Long id, Model model, SessionStatus status) throws SQLException {
        model.addAttribute("titulo", "Editar Comic");

        if (result.hasErrors()) {
            return "comic/editar";
        }

        comic.setUsuario(usuarioService.findUserById(usuarioSeleccionado.getId()));
        Comic comicBD = comicService.updateComic(comic);

        status.setComplete();

        return "redirect:/comic";
    }

    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable Long id, Model model) throws SQLException {
        model.addAttribute("titulo", "Eliminar Comic");

        Comic comic = comicService.findComicById(id);
        model.addAttribute("comic", comic);

        return "comic/borrar";
    }

    @PostMapping("/borrar/{id}")
    public String borrar(@Valid Comic comic, BindingResult result, @PathVariable Long id, Model model, SessionStatus status) throws SQLException {
        model.addAttribute("titulo", "Eliminar Comic");

        comicService.deleteComic(id);

        status.setComplete();

        return "redirect:/comic";
    }
}
