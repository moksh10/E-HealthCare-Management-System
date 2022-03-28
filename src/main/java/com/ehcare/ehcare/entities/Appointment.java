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
@Table(name="appointment")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","patient","doctor"})
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="appointment_id")
	private int appointmentID;
	
	
	@Column(name="appointment_date")
	@NotEmpty(message = "Appointment date required")
	private Date appointmentDate;
	
	
	@Column(name="health_problem")
	@NotEmpty(message = "Description of health problem required")
	@Size(max=750,message = "Maximum length of description of health problem exceeded")
	private String healthProblem;
	
	@Column(name="appointment_status")
	@NotEmpty(message = "Appointment status required")
	@Size(max=30,message = "Maximum length of appointment status exceeded")
	private String appointmentStatus;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="patient_id")
	private Patient patient;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="doctor_id")
	private Doctor doctor;
	
	public Appointment() {
		// TODO Auto-generated constructor stub
	}

	

	public Appointment(Date appointmentDate,String healthProblem,String appointmentStatus,Patient patient, Doctor doctor) {
		this.appointmentDate = appointmentDate;
		this.healthProblem = healthProblem;
		this.appointmentStatus = appointmentStatus;
		this.patient = patient;
		this.doctor = doctor;
	}



	public int getAppointmentID() {
		return appointmentID;
	}

	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}



	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}



	public String getHealthProblem() {
		return healthProblem;
	}

	public void setHealthProblem(String healthProblem) {
		this.healthProblem = healthProblem;
	}

	public String getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
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
