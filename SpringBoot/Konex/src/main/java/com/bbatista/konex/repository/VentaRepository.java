package com.bbatista.konex.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bbatista.konex.model.entity.VentaEntity;

@Repository("inventarioRepository")
public interface VentaRepository extends JpaRepository<VentaEntity, Long>{
	
	List<VentaEntity> findByFechaTransaccionBetween(Date fechaInicio, Date fechaFin);

	List<VentaEntity> findByFechaTransaccionAfter(Date fecha);

	List<VentaEntity> findByFechaTransaccionBefore(Date fecha);

}
