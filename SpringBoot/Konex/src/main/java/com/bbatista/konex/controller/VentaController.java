package com.bbatista.konex.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.bbatista.konex.exception.CustomException;
import com.bbatista.konex.model.dto.Response;
import com.bbatista.konex.model.dto.VentaDto;
import com.bbatista.konex.service.impl.VentaServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/venta")
@CrossOrigin(origins="*")
@Tag(name = "Venta", description = "")
public class VentaController {
	
	@Autowired
	private VentaServiceImpl servicio;
	
	@GetMapping
	public ResponseEntity<Response<List<VentaDto>>> listarVentas(@RequestParam(value = "fechaInicio", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio, @RequestParam(value = "fechaFin", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin){
		return new ResponseEntity<>(new Response<>(HttpStatus.OK.value(), "", servicio.findByFechaTransaccionBetween(fechaInicio, fechaFin)), null, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Response<VentaDto>> agregarVenta(@RequestBody VentaDto ventaDto) throws CustomException {
		return new ResponseEntity<>(new Response<>(HttpStatus.OK.value(), "", servicio.save(ventaDto)), null, HttpStatus.OK);
	}
	
	@ExceptionHandler({ Exception.class, NotFoundException.class, RuntimeException.class, CustomException.class })
	public ResponseEntity<Response<String>> handleException(Exception ex, WebRequest request) {
		return new ResponseEntity<>(new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
