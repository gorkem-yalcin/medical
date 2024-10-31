package com.medical.gorkem.service.medicalrecord;

import com.medical.gorkem.exception.MedicalException;
import com.medical.gorkem.service.medicalrecord.response.ResponseDeleteAllMedicalRecords;
import com.medical.gorkem.service.medicalrecord.response.ResponseFetchAllMedicalRecords;
import com.medical.gorkem.service.medicalrecord.response.ResponseFetchMedicalRecordByCode;
import com.medical.gorkem.service.medicalrecord.response.ResponseUploadMedicalRecords;
import org.springframework.web.multipart.MultipartFile;

public interface MedicalRecordService {

	ResponseUploadMedicalRecords uploadMedicalRecords(MultipartFile file) throws MedicalException;

	ResponseFetchAllMedicalRecords fetchAllMedicalRecords();

	ResponseFetchMedicalRecordByCode fetchMedicalRecordByCode(String code) throws MedicalException;

	ResponseDeleteAllMedicalRecords deleteAllMedicalRecords();

}
