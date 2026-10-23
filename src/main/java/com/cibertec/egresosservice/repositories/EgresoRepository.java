package com.cibertec.egresosservice.repositories;
import com.cibertec.egresosservice.entities.Egreso;
import com.cibertec.egresosservice.entities.enums.EstadoEgreso;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
public interface EgresoRepository extends JpaRepository<Egreso,Long> {
    List<Egreso> findByFechaBetween(LocalDate desde,LocalDate hasta);
    List<Egreso> findByEstado(EstadoEgreso estado);
    List<Egreso> findByNumeroDocumento(String numeroDocumento);
}
