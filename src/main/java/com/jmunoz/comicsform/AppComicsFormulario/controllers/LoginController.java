package com.jmunoz.comicsform.AppComicsFormulario.controllers;

import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Usuario;
import com.jmunoz.comicsform.AppComicsFormulario.services.UsuarioService;
import com.jmunoz.comicsform.AppComicsFormulario.validation.UsuarioLoginValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping({"login", "", "/"})
public class LoginController {

    @Autowired
    private UsuarioLoginValidador validador;

    @Autowired
    private UsuarioService usuarioService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validador);
    }

    @GetMapping({"/", ""})
    public String login(Model model) {
        model.addAttribute("titulo", "LOGIN");

        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);

        return "user/login";
    }

    @PostMapping({"/", ""})
    public String login(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes redirectAttributes) throws SQLException {
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
            return "user/login";
        }

        Usuario usuarioBD = usuarioService.findUsuarioByUsername(usuario.getUsername());

        redirectAttributes.addAttribute("idUsuario", usuarioBD.getId());
        return "redirect:/comic";
    }
}
