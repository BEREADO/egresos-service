package com.cibertec.egresosservice.models;
import com.cibertec.egresosservice.entities.enums.EstadoEgreso;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
public record EgresoDTO(Long id,
    @NotBlank @Size(max=30) String numeroDocumento,
    @NotBlank @Size(max=150) String proveedor,
    @NotNull LocalDate fecha,
    @NotNull @Positive BigDecimal importe,
    @Size(max=255) String documentoAsociado,
    @Size(max=255) String motivo,
    EstadoEgreso estado,
    Long usuarioId,
    @Size(max=255) String comprobanteUrl) {}
