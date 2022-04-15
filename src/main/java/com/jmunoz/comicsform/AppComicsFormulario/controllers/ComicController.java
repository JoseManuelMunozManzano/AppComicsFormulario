package com.jmunoz.comicsform.AppComicsFormulario.controllers;

import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("comic")
public class ComicController {

    @GetMapping("")
    public String ver(Model model, @ModelAttribute Usuario usuario) {
        model.addAttribute("titulo", "COMICS DE " + usuario.getUsername().toUpperCase());

        return "comic";
    }
}
