package com.ehcare.ehcare.services;

import java.util.List;

import com.ehcare.ehcare.entities.Appointment;


public interface AppointmentService {

	public Appointment saveAppointment(int patientID,int doctorID,Appointment appointment);
	public Appointment updateAppointment(int appointmentID,Appointment appointment);
	public Appointment getAppointment(int appointmentID);
	public void deleteAppointment(int appointmentID);
	public List<Appointment> getAllAppointmentsByPatient(int patientID);
	public List<Appointment> getAllAppointmentsByDoctor(int doctorID);
	public void deleteAppointmentByDoctor(int doctorID);
}
