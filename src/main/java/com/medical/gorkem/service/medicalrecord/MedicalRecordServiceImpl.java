package com.medical.gorkem.service.medicalrecord;

import com.medical.gorkem.entity.MedicalRecord;
import com.medical.gorkem.exception.MedicalError;
import com.medical.gorkem.exception.MedicalException;
import com.medical.gorkem.repository.MedicalRecordRepository;
import com.medical.gorkem.service.medicalrecord.response.ResponseDeleteAllMedicalRecords;
import com.medical.gorkem.service.medicalrecord.response.ResponseFetchAllMedicalRecords;
import com.medical.gorkem.service.medicalrecord.response.ResponseFetchMedicalRecordByCode;
import com.medical.gorkem.service.medicalrecord.response.ResponseUploadMedicalRecords;
import com.medical.gorkem.service.medicalrecord.util.MedicalRecordServiceMapper;
import com.medical.gorkem.util.StringUtil;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

	private MedicalRecordRepository medicalRecordRepository;

	public MedicalRecordServiceImpl(MedicalRecordRepository medicalRecordRepository) {
		this.medicalRecordRepository = medicalRecordRepository;
	}

	@Override
	public ResponseUploadMedicalRecords uploadMedicalRecords(MultipartFile file) throws MedicalException {

		try (Reader reader = new InputStreamReader(file.getInputStream());
			 CSVReader csvReader = new CSVReaderBuilder(reader).build()) {

			Iterator<String[]> csvRecordIterator = csvReader.iterator();

			if (csvRecordIterator.hasNext()) {
				csvRecordIterator.next(); //first line is the header
			}
			List<MedicalRecord> medicalRecordList = MedicalRecordServiceMapper.getRecords(csvRecordIterator);

			medicalRecordRepository.saveAll(medicalRecordList);

			return MedicalRecordServiceMapper.getResponseUploadMedicalRecords();
		} catch (Exception e) {
			throw new MedicalException(MedicalError.PROBLEM_OCCURRED_WHEN_READING_THE_FILE);
		}

	}

	@Override
	public ResponseFetchAllMedicalRecords fetchAllMedicalRecords() {

		List<MedicalRecord> medicalRecordList = medicalRecordRepository.findAll();

		return MedicalRecordServiceMapper.getResponseFetchAllMedicallyRecords(medicalRecordList);
	}

	@Override
	public ResponseFetchMedicalRecordByCode fetchMedicalRecordByCode(String code) throws MedicalException {

		if (StringUtil.isEmpty(code)) {
			throw new MedicalException(MedicalError.FETCH_RECORD_BY_CODE_CODE_IS_EMPTY);
		}

		Optional<MedicalRecord> optionalRecord = medicalRecordRepository.findMedicalRecordByCode(code);

		if (optionalRecord.isEmpty()) {
			throw new MedicalException(MedicalError.FETCH_RECORD_BY_CODE_RECORD_NOT_FOUND);
		}

		MedicalRecord foundMedicalRecord = optionalRecord.get();

		return MedicalRecordServiceMapper.getResponseFetchMedicalRecordByCode(foundMedicalRecord);
	}

	@Override
	public ResponseDeleteAllMedicalRecords deleteAllMedicalRecords() {

		medicalRecordRepository.deleteAll();

		return MedicalRecordServiceMapper.getResponseDeleteAllMedicalRecords();
	}

}
