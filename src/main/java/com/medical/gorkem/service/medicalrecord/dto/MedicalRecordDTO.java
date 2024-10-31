package com.medical.gorkem.service.medicalrecord.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicalRecordDTO {

	private String code;
	private String source;
	private String codeListCode;
	private String displayValue;
	private String longDescription;
	private LocalDate fromDate;
	private LocalDate toDate;
	private String sortingPriority;

}
