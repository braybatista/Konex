package com.bbatista.konex.model.entity;

import java.util.Date;

import com.bbatista.konex.model.dto.VentaDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "K_VENTAS")
public class VentaEntity {
	
	@Id
	@Column(name = "V_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "V_FECHA_TRANSACCION")
	private Date fechaTransaccion;
	
	@Column(name = "V_HORA_TRANSACCION")
	private Date horaTransaccion;
	
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "V_MEDICAMENTO", referencedColumnName = "M_ID")
	private MedicamentoEntity medicamento;
	
	@Column(name = "V_CANTIDAD")
	private Integer cantidad;
	
	@Column(name = "V_VALOR_UNITARIO")
	private Double valorUnitario;
	
	@Column(name = "V_VALOR_TOTAL")
	private Double valorTotal;
	
	public VentaEntity(VentaDto venta){
		this.id = venta.getId();
		this.fechaTransaccion = new Date();
		this.medicamento = new MedicamentoEntity(venta.getMedicamento());
		this.cantidad = venta.getCantidad();
		this.valorUnitario = venta.getValorUnitario();
		this.valorTotal = venta.getCantidad() * venta.getValorUnitario();
	}

}
