package com.medical.gorkem.repository;

import com.medical.gorkem.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

	Optional<MedicalRecord> findMedicalRecordByCode(String code);

}
