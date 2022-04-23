package com.jmunoz.comicsform.AppComicsFormulario.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String error404Handler(NoHandlerFoundException ex, Model model) {
        model.addAttribute("error", "Página no encontrada");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());

        return "error/404";
    }
}
