package com.bbatista.konex.model.entity;

import java.util.Date;

import com.bbatista.konex.model.dto.MedicamentoDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "K_MEDICAMENTOS")
public class MedicamentoEntity {

	@Id
	@Column(name = "M_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "M_NOMBRE", nullable = false, length = 45)
	private String nombre;

	@Column(name = "M_LABORATORIO", nullable = false, length = 45)
	private String laboratorio;

	@Column(name = "M_FECHA_FABRICACION", nullable = false)
	private Date fechaFabricacion;
	
	@Column(name = "M_FECHA_VENCIMIENTO", nullable = false)
	private Date fechaVencimiento;

	@Column(name = "M_STOCK", nullable = false)
	private Integer stock;

	@Column(name = "M_VALOR_UNITARIO", nullable = false)
	private Double valorUnitario;
	
	public MedicamentoEntity(MedicamentoDto medicamento) {
		this.id = medicamento.getId();
		this.nombre = medicamento.getNombre();
		this.laboratorio = medicamento.getLaboratorio();
		this.fechaFabricacion = medicamento.getFechaFabricacion();
		this.fechaVencimiento = medicamento.getFechaVencimiento();
		this.stock = medicamento.getStock();
		this.valorUnitario = medicamento.getValorUnitario();
	}

}
