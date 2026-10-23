package com.cibertec.egresosservice.models;
import java.util.List;
public record EgresoCargaMasivaResultado(int totalFilas,int registrados,List<String> errores) {}
