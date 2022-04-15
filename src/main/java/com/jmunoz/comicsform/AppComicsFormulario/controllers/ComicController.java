package com.jmunoz.comicsform.AppComicsFormulario.controllers;

import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Comic;
import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Usuario;
import com.jmunoz.comicsform.AppComicsFormulario.services.ComicService;
import com.jmunoz.comicsform.AppComicsFormulario.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String ver(Model model, @ModelAttribute Usuario usuario) throws SQLException {
        model.addAttribute("titulo", "COMICS DE " + usuario.getUsername().toUpperCase());

        Usuario usuarioBD = usuarioService.findUsuarioByUsername(usuario.getUsername());
        List<Comic> comics = comicService.findComicsByUserId(usuarioBD.getId());

        model.addAttribute("comics", comics);
        model.addAttribute("usuario", usuarioBD);
        return "comic";
    }
}
