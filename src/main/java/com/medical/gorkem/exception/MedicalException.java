package com.medical.gorkem.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
@Setter
public class MedicalException extends Exception {

	@Serial
	private static final long serialVersionUID = 4467971576050786472L;

	private final String errorCode;
	private final int statusCode;

	public MedicalException(MedicalError medicalError) {
		super(medicalError.getErrorMessage());
		this.errorCode = medicalError.getErrorCode();
		this.statusCode = medicalError.getHttpStatusCode();
	}

	public MedicalException(String errorMessage, String errorCode) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
	}

	public MedicalException(MedicalException medicalException) {
		super(medicalException.getMessage());
		this.errorCode = medicalException.getErrorCode();
		this.statusCode = medicalException.getStatusCode();
	}
}