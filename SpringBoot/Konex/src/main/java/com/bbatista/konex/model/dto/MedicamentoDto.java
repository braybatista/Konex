package com.bbatista.konex.model.dto;

import java.util.Date;

import com.bbatista.konex.model.entity.MedicamentoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicamentoDto {

	private Long id;

	private String nombre;

	private String laboratorio;

	private Date fechaFabricacion;
	
	private Date fechaVencimiento;

	private Integer stock;

	private Double valorUnitario;
	
	public MedicamentoDto(MedicamentoEntity medicamento) {
		this.id = medicamento.getId();
		this.nombre = medicamento.getNombre();
		this.laboratorio = medicamento.getLaboratorio();
		this.fechaFabricacion = medicamento.getFechaFabricacion();
		this.fechaVencimiento = medicamento.getFechaVencimiento();
		this.stock = medicamento.getStock();
		this.valorUnitario = medicamento.getValorUnitario();
	}

}
