package com.bbatista.konex;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.bbatista.konex.controller.VentaController;
import com.bbatista.konex.exception.CustomException;
import com.bbatista.konex.model.dto.Response;
import com.bbatista.konex.model.dto.VentaDto;
import com.bbatista.konex.model.entity.MedicamentoEntity;
import com.bbatista.konex.model.entity.VentaEntity;
import com.bbatista.konex.repository.MedicamentoRepository;
import com.bbatista.konex.repository.VentaRepository;

class VentaControllerTest extends KonexApplicationTests {
	
	@Autowired
	private VentaController controller;
	
	@MockBean
	private VentaRepository ventaRepo;
	
	@MockBean
	private MedicamentoRepository medicamentoRepo;
	
	@Test
	void findAll_Success_ReturnData_BothDatesPresent() {
		List<VentaEntity> listarVentas = new ArrayList<>();
		Date fechaInicio = new Date();
		Date fechaFin = new Date();
		
		listarVentas.add(new VentaEntity(1L, new Date(), new Date(), new MedicamentoEntity(1L, "mocked Data 1", "mocked lab 1", new Date(), new Date(), 1, 2000.00), 1, 2000.00, 2000.00));
		listarVentas.add(new VentaEntity(2L, new Date(), new Date(), new MedicamentoEntity(2L, "mocked Data 2", "mocked lab 2", new Date(), new Date(), 1, 2000.00), 1, 2000.00, 2000.00));
		listarVentas.add(new VentaEntity(3L, new Date(), new Date(), new MedicamentoEntity(3L, "mocked Data 3", "mocked lab 3", new Date(), new Date(), 1, 2000.00), 1, 2000.00, 2000.00));
		
		when(ventaRepo.findByFechaTransaccionBetween(any(), any())).thenReturn(listarVentas);
		
		ResponseEntity<Response<List<VentaDto>>> listarMocked = controller.listarVentas(fechaInicio, fechaFin);
		
		assertNotNull(listarMocked);
		assertNotNull(listarMocked.getBody());
		assertNotNull(listarMocked.getBody().getBodyResponse());
		assertSame(listarMocked.getBody().getBodyResponse().size(), listarVentas.size());
	}
	
	@Test
	void findAll_Success_ReturnData_OnlyInitDatePresent() {
		List<VentaEntity> listarVentas = new ArrayList<>();
		Date fechaInicio = new Date();
		Date fechaFin = null;
		
		listarVentas.add(new VentaEntity(1L, new Date(), new Date(), new MedicamentoEntity(1L, "mocked Data 1", "mocked lab 1", new Date(), new Date(), 1, 2000.00), 1, 2000.00, 2000.00));
		listarVentas.add(new VentaEntity(2L, new Date(), new Date(), new MedicamentoEntity(2L, "mocked Data 2", "mocked lab 2", new Date(), new Date(), 1, 2000.00), 1, 2000.00, 2000.00));
		listarVentas.add(new VentaEntity(3L, new Date(), new Date(), new MedicamentoEntity(3L, "mocked Data 3", "mocked lab 3", new Date(), new Date(), 1, 2000.00), 1, 2000.00, 2000.00));
		
		when(ventaRepo.findByFechaTransaccionAfter(any())).thenReturn(listarVentas);
		
		ResponseEntity<Response<List<VentaDto>>> listarMocked = controller.listarVentas(fechaInicio, fechaFin);
		
		assertNotNull(listarMocked);
		assertNotNull(listarMocked.getBody());
		assertNotNull(listarMocked.getBody().getBodyResponse());
		assertSame(listarMocked.getBody().getBodyResponse().size(), listarVentas.size());
	}
	
	@Test
	void findAll_Success_ReturnData_OnlyFinalDatePresent() {
		List<VentaEntity> listarVentas = new ArrayList<>();
		Date fechaInicio = null;
		Date fechaFin = new Date();
		
		listarVentas.add(new VentaEntity(1L, new Date(), new Date(), new MedicamentoEntity(1L, "mocked Data 1", "mocked lab 1", new Date(), new Date(), 1, 2000.00), 1, 2000.00, 2000.00));
		listarVentas.add(new VentaEntity(2L, new Date(), new Date(), new MedicamentoEntity(2L, "mocked Data 2", "mocked lab 2", new Date(), new Date(), 1, 2000.00), 1, 2000.00, 2000.00));
		listarVentas.add(new VentaEntity(3L, new Date(), new Date(), new MedicamentoEntity(3L, "mocked Data 3", "mocked lab 3", new Date(), new Date(), 1, 2000.00), 1, 2000.00, 2000.00));
		
		when(ventaRepo.findByFechaTransaccionBefore(any())).thenReturn(listarVentas);
		
		ResponseEntity<Response<List<VentaDto>>> listarMocked = controller.listarVentas(fechaInicio, fechaFin);
		
		assertNotNull(listarMocked);
		assertNotNull(listarMocked.getBody());
		assertNotNull(listarMocked.getBody().getBodyResponse());
		assertSame(listarMocked.getBody().getBodyResponse().size(), listarVentas.size());
	}
	
	@Test
	void findAll_Success_ReturnData_BothDatesNotPresent() {
		List<VentaEntity> listarVentas = new ArrayList<>();
		Date fechaInicio = null;
		Date fechaFin = null;
		
		listarVentas.add(new VentaEntity(1L, new Date(), new Date(), new MedicamentoEntity(1L, "mocked Data 1", "mocked lab 1", new Date(), new Date(), 1, 2000.00), 1, 2000.00, 2000.00));
		listarVentas.add(new VentaEntity(2L, new Date(), new Date(), new MedicamentoEntity(2L, "mocked Data 2", "mocked lab 2", new Date(), new Date(), 1, 2000.00), 1, 2000.00, 2000.00));
		listarVentas.add(new VentaEntity(3L, new Date(), new Date(), new MedicamentoEntity(3L, "mocked Data 3", "mocked lab 3", new Date(), new Date(), 1, 2000.00), 1, 2000.00, 2000.00));
		
		when(ventaRepo.findAll()).thenReturn(listarVentas);
		
		ResponseEntity<Response<List<VentaDto>>> listarMocked = controller.listarVentas(fechaInicio, fechaFin);
		
		assertNotNull(listarMocked);
		assertNotNull(listarMocked.getBody());
		assertNotNull(listarMocked.getBody().getBodyResponse());
		assertSame(listarMocked.getBody().getBodyResponse().size(), listarVentas.size());
	}
	
	@Test
	void save_Success_ReturnData() throws Exception {
		VentaEntity entityMocked = new VentaEntity(1L, new Date(), new Date(), new MedicamentoEntity(1L, "mocked Data 1", "mocked lab 1", new Date(), new Date(), 1, 2000.00), 1, 2000.00, 2000.00);
		
		when(ventaRepo.save(any(VentaEntity.class))).thenReturn(entityMocked);
		
		ResponseEntity<Response<VentaDto>> agregarMocked = controller.agregarVenta(new VentaDto(entityMocked));
		
		assertNotNull(agregarMocked);
		assertNotNull(agregarMocked.getBody());
		assertSame(agregarMocked.getBody().getBodyResponse().getId(), entityMocked.getId());
	}
	
	@Test
	void save_Exception_ReturnData() throws CustomException {
		VentaEntity entityMocked = new VentaEntity(1L, new Date(), new Date(), new MedicamentoEntity(1L, "mocked Data 1", "mocked lab 1", new Date(), new Date(), 1, 2000.00), 2, 2000.00, 2000.00);
		
		try {
			controller.agregarVenta(new VentaDto(entityMocked));
		} catch(CustomException ex) {
			assertSame("error al intentar crear la venta, no hay suficiente stock de este medicamento.", ex.getStatusMessage());
		}
		
	}
	
	@Test
	void handleException_Success_ReturnData() {		
		ResponseEntity<Response<String>> editarMocked = controller.handleException(new Exception("mocked Exception"), null);
		
		assertNotNull(editarMocked);
		assertNotNull(editarMocked.getBody());
	}

}
