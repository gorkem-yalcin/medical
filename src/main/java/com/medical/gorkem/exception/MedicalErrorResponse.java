package com.medical.gorkem.exception;

import com.medical.gorkem.util.DateUtil;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class MedicalErrorResponse {

	private int statusCode;
	private String errorMessage;
	private String errorCode;
	private long timestamp;
	private String errorDate;

	public MedicalErrorResponse(MedicalException medicalException) {
		this.statusCode = medicalException.getStatusCode();
		this.errorMessage = medicalException.getMessage();
		this.errorCode = medicalException.getErrorCode();
		this.timestamp = System.currentTimeMillis();
		this.errorDate = DateUtil.getNowDateWithTimeString();
	}

	public MedicalErrorResponse(Exception exception) {
		this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
		this.errorMessage = exception.getMessage();
		this.errorCode = "000000";
		this.timestamp = System.currentTimeMillis();
		this.errorDate = DateUtil.getNowDateWithTimeString();
	}

}
