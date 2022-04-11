package com.ehcare.ehcare.services;

import java.sql.Date;
import java.util.List;

import com.ehcare.ehcare.entities.Appointment;


public interface AppointmentService {

	public Appointment saveAppointment(int patientID,int doctorID,Appointment appointment);
	public Appointment updateAppointment(int appointmentID,Appointment appointment);
	public Appointment getAppointment(int appointmentID);
	public List<Appointment> getAllAppointmentsByAppointmentDate(Date appointmentDate);
	public List<Appointment> getAllAppointmentsByDoctorAndAppointmentDate(int doctorID, Date appointmentDate);
	public List<Appointment> getAllAppointmentsByPatientAndAppointmentDate(int patientID, Date appointmentDate);
	public void deleteAppointment(int appointmentID);
	public List<Appointment> getAllAppointmentsByPatient(int patientID);
	public List<Appointment> getAllAppointmentsByDoctor(int doctorID);
	public void deleteAppointmentByDoctor(int doctorID);
	public void deleteAppointmentsByAppointmentDate(Date appointmentDate);
	
}
