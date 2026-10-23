package com.cibertec.egresosservice.entities;
import com.cibertec.egresosservice.entities.enums.EstadoEgreso;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Table(name="egresos")
public class Egreso {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(name="numero_documento",nullable=false,length=30) private String numeroDocumento;
    @Column(nullable=false,length=150) private String proveedor;
    @Column(nullable=false) private LocalDate fecha;
    @Column(nullable=false,precision=12,scale=2) private BigDecimal importe;
    @Column(name="documento_asociado",length=255) private String documentoAsociado;
    @Column(length=255) private String motivo;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private EstadoEgreso estado=EstadoEgreso.REGISTRADO;
    // La entidad Usuario es propiedad de auth-service. No usar @ManyToOne aqui.
    @Column(name="usuario_id",nullable=false) private Long usuarioId;
    @Column(name="comprobante_url",length=255) private String comprobanteUrl;
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getNumeroDocumento(){return numeroDocumento;} public void setNumeroDocumento(String v){numeroDocumento=v;}
    public String getProveedor(){return proveedor;} public void setProveedor(String v){proveedor=v;}
    public LocalDate getFecha(){return fecha;} public void setFecha(LocalDate v){fecha=v;}
    public BigDecimal getImporte(){return importe;} public void setImporte(BigDecimal v){importe=v;}
    public String getDocumentoAsociado(){return documentoAsociado;} public void setDocumentoAsociado(String v){documentoAsociado=v;}
    public String getMotivo(){return motivo;} public void setMotivo(String v){motivo=v;}
    public EstadoEgreso getEstado(){return estado;} public void setEstado(EstadoEgreso v){estado=v;}
    public Long getUsuarioId(){return usuarioId;} public void setUsuarioId(Long v){usuarioId=v;}
    public String getComprobanteUrl(){return comprobanteUrl;} public void setComprobanteUrl(String v){comprobanteUrl=v;}
}
