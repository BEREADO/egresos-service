package com.cibertec.egresosservice.clients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name="auth-service")
public interface AuthClient {
 record UsuarioInfo(Long id,Boolean activo) {}
 @GetMapping("/api/usuarios/{id}") UsuarioInfo buscarUsuario(@PathVariable("id") Long id);
}
