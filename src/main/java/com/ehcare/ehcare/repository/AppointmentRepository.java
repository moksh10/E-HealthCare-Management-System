package com.ehcare.ehcare.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ehcare.ehcare.entities.Appointment;
import com.ehcare.ehcare.entities.Doctor;
import com.ehcare.ehcare.entities.Patient;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Integer>{

    List<Appointment> findAppointmentsByPatient(Patient patient);
	List<Appointment> findAppointmentsByDoctor(Doctor doctor);
	List<Appointment> findAppointmentsByAppointmentDate(Date appointmentDate);
	List<Appointment> findAppointmentsByDoctorAndAppointmentDate(Doctor doctor, Date appointmentDate);
	List<Appointment> findAppointmentsByPatientAndAppointmentDate(Patient doctor, Date appointmentDate);
	void deleteAppointmentsByDoctor(Doctor doctor);
	void deleteAppointmentsByAppointmentDate(Date appointmentDate);
}
