package com.bbatista.konex.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbatista.konex.exception.CustomEnumErrorTypes;
import com.bbatista.konex.exception.CustomException;
import com.bbatista.konex.model.dto.VentaDto;
import com.bbatista.konex.model.entity.VentaEntity;
import com.bbatista.konex.repository.MedicamentoRepository;
import com.bbatista.konex.repository.VentaRepository;
import com.bbatista.konex.service.VentaService;

import jakarta.transaction.Transactional;

@Service
public class VentaServiceImpl implements VentaService{
	
	@Autowired
	private VentaRepository repositorio;
	
	@Autowired
	private MedicamentoRepository repositorioMedicamento;

	@Override
	public List<VentaDto> findByFechaTransaccionBetween(Date fechaInicio, Date fechaFin) {
		List<VentaEntity> listaInventario = new ArrayList<>();
		
		if(fechaInicio == null && fechaFin == null) {
			listaInventario = repositorio.findAll();
		} 
		if(fechaInicio == null && fechaFin != null) {
			listaInventario = repositorio.findByFechaTransaccionBefore(fechaFin);
		} 
 		if(fechaInicio != null && fechaFin == null) {
			listaInventario = repositorio.findByFechaTransaccionAfter(fechaInicio);
		} 
 		if(fechaInicio != null && fechaFin != null) {
			listaInventario = repositorio.findByFechaTransaccionBetween(fechaInicio, fechaFin);
		}
		
		return listaInventario.stream().map(VentaDto::new).toList();
	}

	@Transactional
	@Override
	public VentaDto save(VentaDto venta) throws CustomException {
		if(venta.getMedicamento().getStock() < venta.getCantidad()) throw new CustomException(CustomEnumErrorTypes.OUT_OF_STOCK);
		VentaEntity returnData = repositorio.save(new VentaEntity(venta));
		repositorioMedicamento.updateInventory(venta.getMedicamento().getId(), venta.getCantidad());
		return new VentaDto(returnData);
	}

}
