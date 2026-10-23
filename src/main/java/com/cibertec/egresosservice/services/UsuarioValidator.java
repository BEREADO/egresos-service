package com.cibertec.egresosservice.services;

import com.cibertec.egresosservice.clients.AuthClient;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioValidator {
    private final AuthClient auth;

    public UsuarioValidator(AuthClient auth) {
        this.auth = auth;
    }

    @CircuitBreaker(name = "validacionUsuarios", fallbackMethod = "falloValidacion")
    public void validar(Long id) {
        if (id == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuarioId es obligatorio");
        try {
            if (!Boolean.TRUE.equals(auth.buscarUsuario(id).activo()))
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuario inactivo");
        } catch (FeignException.NotFound ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado: " + id, ex);
        }
    }

    public void falloValidacion(Long id, Throwable ex) {
        if (ex instanceof ResponseStatusException r && (r.getStatusCode().value() == 400 || r.getStatusCode().value() == 404 || r.getStatusCode().value() == 409))
            throw r;
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "No se pudo validar el usuario en auth-service", ex);
    }
}
