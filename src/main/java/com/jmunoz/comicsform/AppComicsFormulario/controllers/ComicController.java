package com.jmunoz.comicsform.AppComicsFormulario.controllers;

import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Comic;
import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Usuario;
import com.jmunoz.comicsform.AppComicsFormulario.services.ComicService;
import com.jmunoz.comicsform.AppComicsFormulario.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("comic")
public class ComicController {

    @Autowired
    private ComicService comicService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("")
    public String ver(Model model, String idUsuario) throws SQLException {
        Usuario usuarioBD = usuarioService.findUserById(Long.parseLong(idUsuario));

        model.addAttribute("titulo", "COMICS DE " + usuarioBD.getUsername().toUpperCase());

        List<Comic> comics = comicService.findComicsByUserId(usuarioBD.getId());

        model.addAttribute("comics", comics);
        model.addAttribute("usuario", usuarioBD);
        return "comic/comic";
    }

    @GetMapping("alta")
    public String alta(Model model) {
        model.addAttribute("titulo", "Alta de Comic");

        return "comic/alta";
    }

    @GetMapping("editar")
    public String editar(Model model) {
        model.addAttribute("titulo", "Editar Comic");

        return "comic/editar";
    }

    @GetMapping("borrar")
    public String borrar(Model model) {
        model.addAttribute("titulo", "Eliminar Comic");

        return "comic/borrar";
    }
}
