package com.ehcare.ehcare.services;

import java.sql.Date;
import java.util.List;

import com.ehcare.ehcare.entities.MedicalRecord;

public interface MedicalRecordService {
	
	public MedicalRecord saveMedicalRecord(int patientID,int doctorID,MedicalRecord medicalRecord);
	public MedicalRecord updateMedicalRecord(int medicalRecordID,MedicalRecord medicalRecord);
	public MedicalRecord getMedicalRecord(int medicalRecordID);
	public void deleteMedicalRecord(int medicalRecordID);
	public List<MedicalRecord> getAllMedicalRecordsByPatient(int patientID);
	public List<MedicalRecord> getAllMedicalRecordsByDoctor(int doctorID);
	public List<MedicalRecord> getAllMedicalRecordsByMedicalRecordDate(Date date);
}
