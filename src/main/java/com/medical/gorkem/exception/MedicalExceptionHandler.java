package com.medical.gorkem.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class MedicalExceptionHandler {

	private MessageSource messageSource;

	public MedicalExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler
	public ResponseEntity<MedicalErrorResponse> handleException(MedicalException medicalException) {

		medicalException.printStackTrace();

		MedicalErrorResponse error = new MedicalErrorResponse(medicalException);
		Locale locale = LocaleContextHolder.getLocale();
		error.setErrorMessage(messageSource.getMessage("error." + error.getErrorCode(), null, error.getErrorMessage(), locale));

		return new ResponseEntity<>(error, HttpStatus.valueOf(error.getStatusCode()));
	}

	@ExceptionHandler
	public ResponseEntity<MedicalErrorResponse> handleException(Exception exception) {

		exception.printStackTrace();

		MedicalErrorResponse error = new MedicalErrorResponse(exception);

		return new ResponseEntity<>(error, HttpStatus.valueOf(error.getStatusCode()));
	}
}
