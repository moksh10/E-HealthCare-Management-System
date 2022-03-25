package com.ehcare.ehcare.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ehcare.ehcare.entities.Appointment;
import com.ehcare.ehcare.entities.Doctor;
import com.ehcare.ehcare.entities.Patient;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Integer>{

	Set<Appointment> findAppointmentsByPatient(Patient patient);
	Set<Appointment> findAppointmentsByDoctor(Doctor doctor);
	void deleteAppointmentByDoctor(Doctor doctor);
}
