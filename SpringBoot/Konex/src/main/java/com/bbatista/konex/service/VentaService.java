package com.bbatista.konex.service;

import java.util.Date;
import java.util.List;

import com.bbatista.konex.exception.CustomException;
import com.bbatista.konex.model.dto.VentaDto;

public interface VentaService {
	List<VentaDto> findByFechaTransaccionBetween(Date fechaInicio, Date fechaFin);
	
	VentaDto save(VentaDto venta) throws CustomException;
	
}
