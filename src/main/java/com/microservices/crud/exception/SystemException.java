
package com.microservices.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SystemException extends RuntimeException {


	public SystemException() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SystemException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SystemException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SystemException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
