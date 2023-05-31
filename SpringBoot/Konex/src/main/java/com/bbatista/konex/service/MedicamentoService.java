package com.bbatista.konex.service;

import java.util.List;

import com.bbatista.konex.model.dto.MedicamentoDto;

public interface MedicamentoService {
	List<MedicamentoDto> findAll();
	
	MedicamentoDto save(MedicamentoDto medicamento);
	
	void deleteById(Long id);

}
