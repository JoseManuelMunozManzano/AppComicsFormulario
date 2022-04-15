package com.jmunoz.comicsform.AppComicsFormulario.controllers;

import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("register")
public class RegisterController {

    @GetMapping({"/", ""})
    public String register(Model model) {
        model.addAttribute("titulo", "ALTA DE USUARIO");

        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);

        return "register";
    }

    @PostMapping({"/", ""})
    public String register(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "ALTA DE USUARIO");
            return "register";
        }

        redirectAttributes.addFlashAttribute("usuario", usuario);
        return "redirect:/comic";
    }
}
