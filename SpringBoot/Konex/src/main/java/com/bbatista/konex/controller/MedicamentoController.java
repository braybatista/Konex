package com.bbatista.konex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.bbatista.konex.exception.CustomException;
import com.bbatista.konex.model.dto.MedicamentoDto;
import com.bbatista.konex.model.dto.Response;
import com.bbatista.konex.service.impl.MedicamentoServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/medicamento")
@CrossOrigin(origins="*")
@Tag(name = "Medicamento", description = "")
public class MedicamentoController {
	
	@Autowired
	private MedicamentoServiceImpl servicio;
	
	@GetMapping
	public ResponseEntity<Response<List<MedicamentoDto>>> listar(){
		return new ResponseEntity<>(new Response<>(HttpStatus.OK.value(), "", servicio.findAll()), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Response<MedicamentoDto>> agregar(@RequestBody MedicamentoDto medicamentoDto) {
		return new ResponseEntity<>(new Response<>(HttpStatus.OK.value(), "", servicio.save(medicamentoDto)), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Response<MedicamentoDto>> editar(@RequestBody MedicamentoDto medicamentoDto) {
		return new ResponseEntity<>(new Response<>(HttpStatus.OK.value(), "", servicio.save(medicamentoDto)), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<Void>> eliminar(@PathVariable(value="id") Long id) {
		servicio.deleteById(id);
		return new ResponseEntity<>(new Response<>(HttpStatus.OK.value(), "", null), HttpStatus.OK);
	}
	
	@ExceptionHandler({ Exception.class, NotFoundException.class, RuntimeException.class, CustomException.class })
	public ResponseEntity<Response<String>> handleException(Exception ex, WebRequest request) {
		return new ResponseEntity<>(new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
