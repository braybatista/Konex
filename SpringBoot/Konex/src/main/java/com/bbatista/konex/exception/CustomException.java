package com.bbatista.konex.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomException extends Exception {

	private static final long serialVersionUID = 4215451628124319595L;
	private final String statusCode;
	private final String statusMessage;
	private final HttpStatus status;
	
	public CustomException(CustomEnumErrorTypes customError) {
		super(new Exception(customError.getValue()));
		this.statusCode = customError.getCode();
		this.statusMessage = customError.getValue();
		this.status = customError.getStatus();
	}
	
	public CustomException(CustomEnumErrorTypes customError, String args) {
		super(new Exception(customError.getValue()));
		this.statusCode = customError.getCode();
		this.statusMessage = String.format(customError.getValue(), args);
		this.status = customError.getStatus();
	}
	
	public CustomException(CustomEnumErrorTypes customError, Exception e) {
		super(e);
		this.statusCode = customError.getCode();
		this.statusMessage = customError.getValue();
		this.status = customError.getStatus();
	}
	
	public CustomException(CustomEnumErrorTypes customError, String args, Exception e) {
		super(e);
		this.statusCode = customError.getCode();
		this.statusMessage =  String.format(customError.getValue(), args);
		this.status = customError.getStatus();
	}

}
