package com.cibertec.egresosservice.controllers;
import com.cibertec.egresosservice.models.*;
import com.cibertec.egresosservice.services.EgresoService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/api/egresos")
public class EgresoController {
    private final EgresoService service;
    public EgresoController(EgresoService service){this.service=service;}
    @PostMapping
    public ResponseEntity<EgresoDTO> registrar(@Valid @RequestBody EgresoDTO dto,@RequestParam Long usuarioId){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(dto,usuarioId));
    }
    @PostMapping(value="/masivo",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EgresoCargaMasivaResultado> masivo(@RequestParam("archivo") MultipartFile archivo,@RequestParam Long usuarioId) throws IOException {
        if(archivo.isEmpty()) return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrarMasivo(archivo.getInputStream(),archivo.getOriginalFilename(),usuarioId));
    }
    @GetMapping("/mes")
    public List<EgresoDTO> mes(@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate fecha){return service.listarPorMes(fecha);}
    @GetMapping("/{id}") public EgresoDTO buscar(@PathVariable Long id){return service.buscarPorId(id);}
    @PatchMapping("/{id}/anular") public EgresoDTO anular(@PathVariable Long id){return service.anular(id);}
    @PatchMapping("/{id}/procesar") public EgresoDTO procesar(@PathVariable Long id){return service.procesar(id);}
}
