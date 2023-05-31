package com.bbatista.konex;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.bbatista.konex.controller.MedicamentoController;
import com.bbatista.konex.model.dto.MedicamentoDto;
import com.bbatista.konex.model.dto.Response;
import com.bbatista.konex.model.entity.MedicamentoEntity;
import com.bbatista.konex.repository.MedicamentoRepository;

class MedicamentoControllerTest extends KonexApplicationTests{
	
	@Autowired
	private MedicamentoController controller;
	
	@MockBean
	private MedicamentoRepository repositorio;
	
	@Test
	void findAll_Success_ReturnData() {
		List<MedicamentoEntity> listaInventario = new ArrayList<>();
		
		listaInventario.add(new MedicamentoEntity(1L, "mocked Data 1", "mocked lab 1", new Date(), new Date(), 1, 2000.00));
		listaInventario.add(new MedicamentoEntity(2L, "mocked Data 2", "mocked lab 2", new Date(), new Date(), 1, 2000.00));
		listaInventario.add(new MedicamentoEntity(3L, "mocked Data 3", "mocked lab 3", new Date(), new Date(), 1, 2000.00));
		
		when(repositorio.findAll()).thenReturn(listaInventario);
		
		ResponseEntity<Response<List<MedicamentoDto>>> listarMocked = controller.listar();
		
		assertNotNull(listarMocked);
		assertNotNull(listarMocked.getBody());
		assertNotNull(listarMocked.getBody().getBodyResponse());
		assertSame(listarMocked.getBody().getBodyResponse().size(), listaInventario.size());
	}
	
	@Test
	void save_Success_ReturnData() {
		MedicamentoEntity entityMocked = new MedicamentoEntity(1L, "mocked Data 1", "mocked lab 1", new Date(), new Date(), 1, 2000.00);
		
		when(repositorio.save(any(MedicamentoEntity.class))).thenReturn(entityMocked);
		
		ResponseEntity<Response<MedicamentoDto>> agregarMocked = controller.agregar(new MedicamentoDto(entityMocked));
		
		assertNotNull(agregarMocked);
		assertNotNull(agregarMocked.getBody());
		assertNotNull(agregarMocked.getBody().getBodyResponse());
		assertSame(agregarMocked.getBody().getBodyResponse().getId(), entityMocked.getId());
	}
	
	@Test
	void editar_Success_ReturnData() {
		MedicamentoEntity entityMocked = new MedicamentoEntity(1L, "mocked Data 1", "mocked lab 1", new Date(), new Date(), 1, 2000.00);
		
		when(repositorio.save(any(MedicamentoEntity.class))).thenReturn(entityMocked);
		
		ResponseEntity<Response<MedicamentoDto>> editarMocked = controller.editar(new MedicamentoDto(entityMocked));
		
		assertNotNull(editarMocked);
		assertNotNull(editarMocked.getBody());
		assertNotNull(editarMocked.getBody().getBodyResponse());
		assertSame(editarMocked.getBody().getBodyResponse().getId(), entityMocked.getId());
	}
	
	@Test
	void eliminar_Success_ReturnData() {
		MedicamentoEntity entityMocked = new MedicamentoEntity(1L, "mocked Data 1", "mocked lab 1", new Date(), new Date(), 1, 2000.00);
		
		doNothing().when(repositorio).deleteById(anyLong());
		
		ResponseEntity<Response<Void>> editarMocked = controller.eliminar(entityMocked.getId());
		
		assertNotNull(editarMocked);
		assertNotNull(editarMocked.getBody());
		assertNull(editarMocked.getBody().getBodyResponse());
	}
	
	@Test
	void handleException_Success_ReturnData() {		
		ResponseEntity<Response<String>> editarMocked = controller.handleException(new Exception("mocked Exception"), null);
		
		assertNotNull(editarMocked);
		assertNotNull(editarMocked.getBody());
	}
	
}
