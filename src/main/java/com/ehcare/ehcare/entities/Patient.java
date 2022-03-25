package com.ehcare.ehcare.entities;


import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


@Entity
@Table(name="patient")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","appointments","medicalRecords"})
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="patientID")
	private int patientID;
	
	@Column(name="patientEmail")
	@Email(message = "Not a valid email")
	@NotEmpty(message = "Patient email required")
	@Size(max=200,message = "Email length exceeded: 200")
	private String patientEmail;
	
	@Column(name="password")
	@NotEmpty(message = "Password required")
	@Size(min=8,message = "Password must be minimum of 8 characters")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@Column(name="patientName")
	@NotEmpty(message = "Patient name cannot be empty")
	@Size(max=200,message = "Patient name length exceeded: 200")
	private String patientName;
	
	@Column(name="patientContact")
	@NotEmpty(message = "Patient contact cannot be empty")
	@Size(max=12,message = "Patient name length exceeded: 12")
	private String patientContact;
	
	@Column(name="patientAddress")
	@NotEmpty(message = "Patient name cannot be empty")
	@Size(max=400,message = "Patient address length exceeded: 400")
	private String patientAddress;
	
	@Column(name="patientGender")
	@NotEmpty(message = "Patient gender cannot be empty")
	@Size(max=12,message = "Patient gender length exceeded: 12")
	private String patientGender;
	
	@Column(name="patientAge")
	@NotEmpty(message = "Patient age cannot be empty")
	@Min(value=1,message = "Patient age invalid")
	private int patientAge;
	
	@OneToMany(mappedBy = "patient",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Appointment> appointments;

	@OneToMany(mappedBy = "patient",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
	private List<MedicalRecord> medicalRecords;

	
	public Patient() {
		// TODO Auto-generated constructor stub
	}

	public Patient(String patientEmail,String patientName,String patientContact,String patientAddress,String patientGender,int patientAge) {
		this.patientEmail = patientEmail;
		this.patientName = patientName;
		this.patientContact = patientContact;
		this.patientAddress = patientAddress;
		this.patientGender = patientGender;
		this.patientAge = patientAge;
	}

	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientContact() {
		return patientContact;
	}

	public void setPatientContact(String patientContact) {
		this.patientContact = patientContact;
	}

	public String getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	public String getPatientGender() {
		return patientGender;
	}

	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	public int getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public List<MedicalRecord> getMedicalRecords() {
		return medicalRecords;
	}

	public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
		this.medicalRecords = medicalRecords;
	}
	

	
	
	
	
}
