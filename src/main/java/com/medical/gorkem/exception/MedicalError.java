package com.medical.gorkem.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum MedicalError {

	REQUEST_IS_EMPTY("999999", HttpStatus.BAD_REQUEST.value(), "Request is not set"),
	FETCH_RECORD_BY_CODE_CODE_IS_EMPTY("000001", HttpStatus.BAD_REQUEST.value(), "A code field is required for searching medical records by code"),
	FETCH_RECORD_BY_CODE_RECORD_NOT_FOUND("000002", HttpStatus.NOT_FOUND.value(), "Medical record is not found with the given code"),
	PROBLEM_OCCURRED_WHEN_READING_THE_FILE("000003", HttpStatus.BAD_REQUEST.value(), "Error occurred when reading the file"),
	CAN_NOT_INSTANTIATE_A_UTILITY_CLASS("000004", HttpStatus.BAD_REQUEST.value(), "Can not instantiate a utility class");

	private final String errorCode;
	private final int httpStatusCode;
	private final String errorMessage;

	MedicalError(String errorCode, int httpStatusCode, String errorMessage) {
		this.errorCode = errorCode;
		this.httpStatusCode = httpStatusCode;
		this.errorMessage = errorMessage;
	}

}
