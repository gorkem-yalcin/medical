package com.medical.gorkem.controller.medicalrecord;

import com.medical.gorkem.exception.MedicalException;
import com.medical.gorkem.model.BaseResponse;
import com.medical.gorkem.service.medicalrecord.MedicalRecordService;
import com.medical.gorkem.service.medicalrecord.response.ResponseDeleteAllMedicalRecords;
import com.medical.gorkem.service.medicalrecord.response.ResponseFetchAllMedicalRecords;
import com.medical.gorkem.service.medicalrecord.response.ResponseFetchMedicalRecordByCode;
import com.medical.gorkem.service.medicalrecord.response.ResponseUploadMedicalRecords;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/medicalRecords")
public class MedicalRecordController {

	private MedicalRecordService medicalRecordService;

	public MedicalRecordController(MedicalRecordService medicalRecordService) {
		this.medicalRecordService = medicalRecordService;
	}

	@Operation(summary = "Upload medical records")
	@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ResponseUploadMedicalRecords.class), mediaType = "application/json") })
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<BaseResponse> uploadMedicalRecords(@RequestParam(name = "file") MultipartFile file) throws MedicalException {

		ResponseUploadMedicalRecords responseUploadMedicalRecords = medicalRecordService.uploadMedicalRecords(file);

		return new ResponseEntity<>(responseUploadMedicalRecords, HttpStatus.CREATED);
	}

	@Operation(summary = "Fetch all medical records")
	@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ResponseFetchAllMedicalRecords.class), mediaType = "application/json") })
	@GetMapping
	public ResponseEntity<BaseResponse> fetchAllMedicalRecords() {

		ResponseFetchAllMedicalRecords responseFetchAllMedicalRecords = medicalRecordService.fetchAllMedicalRecords();

		return new ResponseEntity<>(responseFetchAllMedicalRecords, HttpStatus.OK);
	}

	@Operation(summary = "Fetch medical record by code")
	@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ResponseFetchMedicalRecordByCode.class), mediaType = "application/json") })
	@GetMapping("/{code}")
	public ResponseEntity<BaseResponse> fetchMedicalRecordByCode(@PathVariable("code") String code) throws MedicalException {

		ResponseFetchMedicalRecordByCode responseFetchMedicalRecordByCode = medicalRecordService.fetchMedicalRecordByCode(code);

		return new ResponseEntity<>(responseFetchMedicalRecordByCode, HttpStatus.OK);
	}

	@Operation(summary = "Delete all medical records")
	@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ResponseDeleteAllMedicalRecords.class), mediaType = "application/json") })
	@DeleteMapping
	public ResponseEntity<BaseResponse> deleteAllMedicalRecords() {

		ResponseDeleteAllMedicalRecords responseDeleteAllMedicalRecords = medicalRecordService.deleteAllMedicalRecords();

		return new ResponseEntity<>(responseDeleteAllMedicalRecords, HttpStatus.OK);
	}
}