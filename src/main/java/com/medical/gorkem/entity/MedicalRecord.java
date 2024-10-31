package com.medical.gorkem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class MedicalRecord {

	@Id
	@Column
	private String code;

	@Column
	private String source;

	@Column
	private String codeListCode;

	@Column
	private String displayValue;

	@Column
	private String longDescription;

	@Column
	private LocalDate fromDate;

	@Column
	private LocalDate toDate;

	@Column
	private String sortingPriority;

}
