package com.medical.gorkem.service.medicalrecord.response;

import com.medical.gorkem.model.BaseResponse;
import com.medical.gorkem.service.medicalrecord.dto.MedicalRecordDTO;
import lombok.Data;

import java.util.List;

@Data
public class ResponseFetchAllMedicalRecords extends BaseResponse {

	private List<MedicalRecordDTO> medicalRecordDTOList;

}
