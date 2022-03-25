package com.ehcare.ehcare.services;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ehcare.ehcare.entities.Appointment;
import com.ehcare.ehcare.entities.Doctor;
import com.ehcare.ehcare.entities.Patient;
import com.ehcare.ehcare.repository.AppointmentRepository;
import com.ehcare.ehcare.repository.DoctorRepository;
import com.ehcare.ehcare.repository.PatientRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService{
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Override
	@Transactional
	public Appointment saveAppointment(int patientID,int doctorID,Appointment appointment) {
		Optional<Doctor> doctor=doctorRepository.findById(doctorID);
		if(!doctor.isPresent())
			throw new RuntimeException();
		appointment.setDoctor(doctor.get());
		Optional<Patient> patient=patientRepository.findById(patientID);
		if(!patient.isPresent())
			throw new RuntimeException();
		appointment.setPatient(patient.get());
		return appointmentRepository.save(appointment);
	}

	@Override
	@Transactional
	public Appointment updateAppointment(int appointmentID, Appointment appointment) {
		Optional<Appointment> appointmentToGet=appointmentRepository.findById(appointmentID);
		if(!appointmentToGet.isPresent())
			throw new RuntimeException();
		Appointment appointmentToUpdate=appointmentToGet.get();
		appointmentToUpdate.setAppointmentDate(appointment.getAppointmentDate());
		appointmentToUpdate.setAppointmentStatus(appointment.getAppointmentStatus());
		appointmentToUpdate.setHealthProblem(appointment.getHealthProblem());
		return appointmentRepository.save(appointmentToUpdate);
	}

	@Override
	@Transactional
	public Appointment getAppointment(int appointmentID) {
		Optional<Appointment> appointmentToGet=appointmentRepository.findById(appointmentID);
		if(!appointmentToGet.isPresent())
			throw new RuntimeException();
		return appointmentToGet.get();
	}

	

	@Override
	@Transactional
	public void deleteAppointment(int appointmentID) {
		Optional<Appointment> appointmentToGet = appointmentRepository.findById(appointmentID);
		if(!appointmentToGet.isPresent())
			throw new RuntimeException();
		Appointment appointmentToDelete = appointmentToGet.get();
	    appointmentRepository.delete(appointmentToDelete);
		
	}

	
	@Override
	@Transactional
	public List<Appointment> getAllAppointmentsByPatient(int patientID) {
		Optional<Patient> patient=patientRepository.findById(patientID);
		if(!patient.isPresent())
			throw new RuntimeException();
		return appointmentRepository.findAppointmentsByPatient(patient.get());
	}

	@Override
	@Transactional
	public List<Appointment> getAllAppointmentsByDoctor(int doctorID) {
		Optional<Doctor> doctor=doctorRepository.findById(doctorID);
		if(!doctor.isPresent())
			throw new RuntimeException();
		return appointmentRepository.findAppointmentsByDoctor(doctor.get());
	}

	@Override
	@Transactional
	public void deleteAppointmentByDoctor(int doctorID) {
		// TODO Auto-generated method stub
		Optional<Doctor> doctor=doctorRepository.findById(doctorID);
		if(!doctor.isPresent())
			throw new RuntimeException();
		appointmentRepository.deleteAppointmentsByDoctor(doctor.get());
	
		
		
	}
}
