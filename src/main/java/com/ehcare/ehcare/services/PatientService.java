package com.ehcare.ehcare.services;

import java.util.List;

import com.ehcare.ehcare.entities.Patient;

public interface PatientService {

	public Patient savePatient(Patient patient);
	public Patient updatePatient(int patientID,Patient patient);
	public Patient getPatient(int patientID);
	public Patient getPatientByPatientEmail(String patientEmail);
	public void deletePatient(int patientID);
	public List<Patient> getAllPatients();
}
