package com.medical.gorkem.service.medicalrecord.response;

import com.medical.gorkem.model.BaseResponse;
import com.medical.gorkem.service.medicalrecord.dto.MedicalRecordDTO;
import lombok.Data;

@Data
public class ResponseFetchMedicalRecordByCode extends BaseResponse {

	private MedicalRecordDTO medicalRecordDTO;

}
