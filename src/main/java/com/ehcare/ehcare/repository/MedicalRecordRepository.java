package com.ehcare.ehcare.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ehcare.ehcare.entities.Doctor;
import com.ehcare.ehcare.entities.MedicalRecord;
import com.ehcare.ehcare.entities.Patient;

@Repository
public interface MedicalRecordRepository extends CrudRepository<MedicalRecord, Integer>{

	List<MedicalRecord> findMedicalRecordsByPatient(Patient patient);
	List<MedicalRecord> findMedicalRecordsByDoctor(Doctor doctor);
	List<MedicalRecord> findMedicalRecordsByMedicalRecordDate(Date date);

}
