package com.ehcare.ehcare.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehcare.ehcare.entities.MedicalRecord;
import com.ehcare.ehcare.entities.Doctor;
import com.ehcare.ehcare.entities.Patient;
import com.ehcare.ehcare.repository.MedicalRecordRepository;
import com.ehcare.ehcare.repository.DoctorRepository;
import com.ehcare.ehcare.repository.PatientRepository;

@Service
public class MedicalRecordImpl implements MedicalRecordService{
	
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Override
	@Transactional
	public MedicalRecord saveMedicalRecord(int patientID,int doctorID,MedicalRecord medicalRecord) {
		Optional<Doctor> doctor=doctorRepository.findById(doctorID);
		if(!doctor.isPresent())
			throw new RuntimeException();
		medicalRecord.setDoctor(doctor.get());
		Optional<Patient> patient=patientRepository.findById(patientID);
		if(!patient.isPresent())
			throw new RuntimeException();
		medicalRecord.setPatient(patient.get());
		return medicalRecordRepository.save(medicalRecord);
	}

	@Override
	@Transactional
	public MedicalRecord updateMedicalRecord(int medicalRecordID, MedicalRecord medicalRecord) {
		Optional<MedicalRecord> medicalRecordToGet=medicalRecordRepository.findById(medicalRecordID);
		if(!medicalRecordToGet.isPresent())
			throw new RuntimeException();
		MedicalRecord medicalRecordToUpdate=medicalRecordToGet.get();
		medicalRecordToUpdate.setMedicalRecordDate(medicalRecord.getMedicalRecordDate());
		medicalRecordToUpdate.setMedicalRecordDiagnosis(medicalRecord.getMedicalRecordDiagnosis());
		medicalRecordToUpdate.setMedicalRecordDrugs(medicalRecord.getMedicalRecordDrugs());
		return medicalRecordRepository.save(medicalRecordToUpdate);
	}

	@Override
	@Transactional
	public MedicalRecord getMedicalRecord(int medicalRecordID) {
		Optional<MedicalRecord> medicalRecordToGet=medicalRecordRepository.findById(medicalRecordID);
		if(!medicalRecordToGet.isPresent())
			throw new RuntimeException();
		return medicalRecordToGet.get();
	}

	

	@Override
	@Transactional
	public void deleteMedicalRecord(int medicalRecordID) {
		Optional<MedicalRecord> medicalRecordToGet = medicalRecordRepository.findById(medicalRecordID);
		if(!medicalRecordToGet.isPresent())
			throw new RuntimeException();
		MedicalRecord medicalRecordToDelete = medicalRecordToGet.get();
	    medicalRecordRepository.delete(medicalRecordToDelete);
		
	}

	
	@Override
	@Transactional
	public List<MedicalRecord> getAllMedicalRecordsByPatient(int patientID) {
		Optional<Patient> patient=patientRepository.findById(patientID);
		if(!patient.isPresent())
			throw new RuntimeException();
		return medicalRecordRepository.findMedicalRecordsByPatient(patient.get());
	}

	@Override
	@Transactional
	public List<MedicalRecord> getAllMedicalRecordsByDoctor(int doctorID) {
		Optional<Doctor> doctor=doctorRepository.findById(doctorID);
		if(!doctor.isPresent())
			throw new RuntimeException();
		return medicalRecordRepository.findMedicalRecordsByDoctor(doctor.get());
	}

	@Override
	@Transactional
	public List<MedicalRecord> getAllMedicalRecordsByMedicalRecordDate(Date date) {
		return medicalRecordRepository.findMedicalRecordsByMedicalRecordDate(date);
	}

	
}
