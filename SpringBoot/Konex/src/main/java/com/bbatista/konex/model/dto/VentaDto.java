package com.bbatista.konex.model.dto;

import java.util.Date;

import com.bbatista.konex.model.entity.VentaEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaDto {
	
	private Long id;
	
	private Date fechaTransaccion;
		
	private MedicamentoDto medicamento;
	
	private Integer cantidad;
	
	private Double valorUnitario;

	private Double valorTotal;
	
	public VentaDto(VentaEntity venta){
		this.id = venta.getId();
		this.fechaTransaccion = venta.getHoraTransaccion();
		this.medicamento = new MedicamentoDto(venta.getMedicamento());
		this.cantidad = venta.getCantidad();
		this.valorUnitario = venta.getValorUnitario();
		this.valorTotal = venta.getCantidad() * venta.getValorUnitario();
	}

}
