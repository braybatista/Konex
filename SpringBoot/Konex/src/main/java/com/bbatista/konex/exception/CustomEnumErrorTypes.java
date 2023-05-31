package com.bbatista.konex.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum CustomEnumErrorTypes {
	
	SEARCH_NOT_FOUND("E001", "no existen resultados para la consulta.", HttpStatus.NOT_FOUND),
	REQUIRED_PARAMS("E002", "los parametros de entrada son requeridos", HttpStatus.BAD_REQUEST),
	OUT_OF_STOCK("E003", "error al intentar crear la venta, no hay suficiente stock de este medicamento.", HttpStatus.BAD_REQUEST),
	EXCEPTION_ERROR("E004", "ha ocurrido una excepcion en tiempo de ejecucion en la funcion %s", HttpStatus.INTERNAL_SERVER_ERROR);
	
	private String code;
	private String value;
	private HttpStatus status;
}
