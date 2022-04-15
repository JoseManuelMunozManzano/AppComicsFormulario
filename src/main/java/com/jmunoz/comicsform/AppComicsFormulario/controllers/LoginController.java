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
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping({"login", "", "/"})
public class LoginController {

    @GetMapping({"/", ""})
    public String login(Model model) {
        model.addAttribute("titulo", "LOGIN");

        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);

        return "login";
    }

    @PostMapping({"/", ""})
    public String login(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        Map<String, String> errores = new HashMap<>();
        if (result.hasErrors()) {
            result.getFieldErrors().forEach(err -> {
                if (!err.getField().contains("email")) {
                    errores.put(err.getField(), err.getDefaultMessage());
                }
            });
        }

        if (!errores.isEmpty()) {
            model.addAttribute("error", errores);
            model.addAttribute("titulo", "LOGIN");
            return "login";
        }

        redirectAttributes.addFlashAttribute("usuario", usuario);
        return "redirect:/comic";
    }
}
