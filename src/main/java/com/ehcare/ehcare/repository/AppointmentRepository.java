package com.ehcare.ehcare.repository;

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
	void deleteAppointmentsByDoctor(Doctor doctor);
}
