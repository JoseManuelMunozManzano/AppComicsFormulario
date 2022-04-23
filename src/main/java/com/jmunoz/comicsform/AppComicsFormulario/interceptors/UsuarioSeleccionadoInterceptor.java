package com.jmunoz.comicsform.AppComicsFormulario.interceptors;

import com.jmunoz.comicsform.AppComicsFormulario.models.domain.UsuarioSeleccionado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UsuarioSeleccionadoInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioSeleccionadoInterceptor.class);

    @Autowired
    private UsuarioSeleccionado usuarioSeleccionado;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("Usuario seleccionado: " + usuarioSeleccionado.getId());

        return true;
    }
}
