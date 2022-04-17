package com.jmunoz.comicsform.AppComicsFormulario.controllers;

import com.jmunoz.comicsform.AppComicsFormulario.models.domain.Usuario;
import com.jmunoz.comicsform.AppComicsFormulario.models.domain.UsuarioSeleccionado;
import com.jmunoz.comicsform.AppComicsFormulario.services.UsuarioService;
import com.jmunoz.comicsform.AppComicsFormulario.validation.UsuarioRegisterValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private UsuarioRegisterValidador validador;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioSeleccionado usuarioSeleccionado;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validador);
    }

    @GetMapping({"/", ""})
    public String register(Model model) {
        model.addAttribute("titulo", "ALTA DE USUARIO");

        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);

        return "user/register";
    }

    @PostMapping({"/", ""})
    public String register(@Valid Usuario usuario, BindingResult result, Model model) throws SQLException {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "ALTA DE USUARIO");
            return "user/register";
        }

        Usuario usuarioBD = usuarioService.saveUsuario(usuario);
        usuarioSeleccionado.setId(usuarioBD.getId());

        return "redirect:/comic/comic";
    }
}
