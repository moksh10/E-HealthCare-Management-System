package com.ehcare.ehcare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ehcare.ehcare.entities.Patient;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Integer>{

	Patient findPatientByPatientEmail(String patientEmail);
}
