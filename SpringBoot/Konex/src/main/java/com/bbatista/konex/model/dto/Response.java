package com.bbatista.konex.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Response<T> {
	private Header header;
	private T bodyResponse;
	
	public Response(int statusCode, String statusMessage, T bodyResponse) {
		this.header = new Header(statusCode, statusMessage);
		this.bodyResponse = bodyResponse;
	}
}
