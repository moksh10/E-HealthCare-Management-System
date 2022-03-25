package com.ehcare.ehcare.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ehcare.ehcare.entities.Doctor;
import com.ehcare.ehcare.entities.MedicalRecord;
import com.ehcare.ehcare.entities.Patient;

@Repository
public interface MedicalRecordRepository extends CrudRepository<MedicalRecord, Integer>{

	Set<MedicalRecord> findMedicalRecordsByPatient(Patient patient);
	Set<MedicalRecord> findMedicalRecordsByDoctor(Doctor doctor);

}
