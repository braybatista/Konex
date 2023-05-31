package com.bbatista.konex.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbatista.konex.model.dto.MedicamentoDto;
import com.bbatista.konex.model.entity.MedicamentoEntity;
import com.bbatista.konex.repository.MedicamentoRepository;
import com.bbatista.konex.service.MedicamentoService;

import jakarta.transaction.Transactional;

@Service
public class MedicamentoServiceImpl implements MedicamentoService{
	
	@Autowired
	private MedicamentoRepository repositorio;

	@Override
	public List<MedicamentoDto> findAll() {
		List<MedicamentoEntity> listaInventario = repositorio.findAll();
		return listaInventario.stream().map(MedicamentoDto::new).toList();
	}

	@Transactional
	@Override
	public MedicamentoDto save(MedicamentoDto medicamento) {
		MedicamentoEntity returnData = repositorio.save(new MedicamentoEntity(medicamento));
		return new MedicamentoDto(returnData);
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		repositorio.deleteById(id);
	}

}
