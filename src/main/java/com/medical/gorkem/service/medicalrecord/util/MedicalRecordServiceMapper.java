package com.medical.gorkem.service.medicalrecord.util;

import com.medical.gorkem.entity.MedicalRecord;
import com.medical.gorkem.exception.MedicalError;
import com.medical.gorkem.exception.MedicalException;
import com.medical.gorkem.service.medicalrecord.dto.MedicalRecordDTO;
import com.medical.gorkem.service.medicalrecord.response.ResponseDeleteAllMedicalRecords;
import com.medical.gorkem.service.medicalrecord.response.ResponseFetchAllMedicalRecords;
import com.medical.gorkem.service.medicalrecord.response.ResponseFetchMedicalRecordByCode;
import com.medical.gorkem.service.medicalrecord.response.ResponseUploadMedicalRecords;
import com.medical.gorkem.util.DateUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MedicalRecordServiceMapper {

	private MedicalRecordServiceMapper() throws MedicalException {
		throw new MedicalException(MedicalError.CAN_NOT_INSTANTIATE_A_UTILITY_CLASS);
	}

	public static List<MedicalRecordDTO> getMedicalRecordDTOList(List<MedicalRecord> medicalRecordList) {
		return medicalRecordList.stream()
				.filter(Objects::nonNull)
				.map(MedicalRecordServiceMapper::getMedicalRecordDTO)
				.collect(Collectors.toList());
	}

	public static MedicalRecordDTO getMedicalRecordDTO(MedicalRecord medicalRecord) {
		MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();
		if (medicalRecord != null) {
			medicalRecordDTO.setCode(medicalRecord.getCode());
			medicalRecordDTO.setSource(medicalRecord.getSource());
			medicalRecordDTO.setCodeListCode(medicalRecord.getCodeListCode());
			medicalRecordDTO.setDisplayValue(medicalRecord.getDisplayValue());
			medicalRecordDTO.setLongDescription(medicalRecord.getLongDescription());
			medicalRecordDTO.setFromDate(medicalRecord.getFromDate());
			medicalRecordDTO.setToDate(medicalRecord.getToDate());
			medicalRecordDTO.setSortingPriority(medicalRecord.getSortingPriority());
		}
		return medicalRecordDTO;
	}

	public static MedicalRecord getMedicalRecordFromReadLine(String[] line) {
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setSource(line[0]);
		medicalRecord.setCodeListCode(line[1]);
		medicalRecord.setCode(line[2]);
		medicalRecord.setDisplayValue(line[3]);
		medicalRecord.setLongDescription(line[4]);
		medicalRecord.setFromDate(DateUtil.getLocalDateFromString(DateUtil.DATE_FORMAT, line[5]));
		medicalRecord.setToDate(DateUtil.getLocalDateFromString(DateUtil.DATE_FORMAT, line[6]));
		medicalRecord.setSortingPriority(line[7]);
		return medicalRecord;
	}

	public static ResponseDeleteAllMedicalRecords getResponseDeleteAllMedicalRecords() {
		ResponseDeleteAllMedicalRecords responseDeleteAllMedicalRecords = new ResponseDeleteAllMedicalRecords();
		responseDeleteAllMedicalRecords.setSuccess(true);
		return responseDeleteAllMedicalRecords;
	}

	public static ResponseFetchAllMedicalRecords getResponseFetchAllMedicallyRecords(List<MedicalRecord> medicalRecordList) {
		ResponseFetchAllMedicalRecords responseFetchAllMedicalRecords = new ResponseFetchAllMedicalRecords();
		responseFetchAllMedicalRecords.setMedicalRecordDTOList(getMedicalRecordDTOList(medicalRecordList));
		return responseFetchAllMedicalRecords;
	}

	public static ResponseUploadMedicalRecords getResponseUploadMedicalRecords() {
		ResponseUploadMedicalRecords responseUploadMedicalRecords = new ResponseUploadMedicalRecords();
		responseUploadMedicalRecords.setSuccess(true);
		return responseUploadMedicalRecords;
	}

	public static ResponseFetchMedicalRecordByCode getResponseFetchMedicalRecordByCode(MedicalRecord foundMedicalRecord) {
		ResponseFetchMedicalRecordByCode responseFetchMedicalRecordByCode = new ResponseFetchMedicalRecordByCode();
		responseFetchMedicalRecordByCode.setMedicalRecordDTO(getMedicalRecordDTO(foundMedicalRecord));
		return responseFetchMedicalRecordByCode;
	}

	public static List<MedicalRecord> getRecords(Iterator<String[]> csvRecordIterator) {
		List<MedicalRecord> medicalRecordList = new ArrayList<>();
		while (csvRecordIterator.hasNext()) {
			String[] line = csvRecordIterator.next();
			if (line != null && line.length > 7) {
				medicalRecordList.add(MedicalRecordServiceMapper.getMedicalRecordFromReadLine(line));
			}
		}
		return medicalRecordList;
	}
}
