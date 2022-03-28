package com.ehcare.ehcare.entities;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="medical_record")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","patient","doctor"})
public class MedicalRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="medicalrecord_id")
	private int medicalRecordID;
	
	@Column(name="medicalrecord_date")
	@NotEmpty(message = "Medical Record date required")
	private Date medicalRecordDate;
	
	@Column(name="medicalrecord_diagnosis")
	@NotEmpty(message = "Medical Record diagnosis required")
	@Size(max=1800,message = "Maximum length of medical record diagnosis exceeded")
	private String medicalRecordDiagnosis;
	
	@Column(name="medicalrecord_drugs")
	@NotEmpty(message = "Medical Record drugs required")
	@Size(max=700,message = "Maximum length of medical record drugs exceeded")
	private String medicalRecordDrugs;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="patient_id")
	private Patient patient;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="doctor_id")
	private Doctor doctor;
	
	public MedicalRecord() {
		// TODO Auto-generated constructor stub
	}

	public MedicalRecord(Date medicalRecordDate,String medicalRecordDiagnosis,String medicalRecordDrugs,Patient patient, Doctor doctor) {
		this.medicalRecordDate = medicalRecordDate;
		this.medicalRecordDiagnosis = medicalRecordDiagnosis;
		this.medicalRecordDrugs = medicalRecordDrugs;
		this.patient = patient;
		this.doctor = doctor;
	}

	public int getMedicalRecordID() {
		return medicalRecordID;
	}

	public void setMedicalRecordID(int medicalRecordID) {
		this.medicalRecordID = medicalRecordID;
	}

	public Date getMedicalRecordDate() {
		return medicalRecordDate;
	}

	public void setMedicalRecordDate(Date medicalRecordDate) {
		this.medicalRecordDate = medicalRecordDate;
	}

	public String getMedicalRecordDiagnosis() {
		return medicalRecordDiagnosis;
	}

	public void setMedicalRecordDiagnosis(String medicalRecordDiagnosis) {
		this.medicalRecordDiagnosis = medicalRecordDiagnosis;
	}

	public String getMedicalRecordDrugs() {
		return medicalRecordDrugs;
	}

	public void setMedicalRecordDrugs(String medicalRecordDrugs) {
		this.medicalRecordDrugs = medicalRecordDrugs;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	
}

	