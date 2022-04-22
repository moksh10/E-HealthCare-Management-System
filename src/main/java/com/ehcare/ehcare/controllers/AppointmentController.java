package com.ehcare.ehcare.controllers;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehcare.ehcare.dto.ResponseSuccess;
import com.ehcare.ehcare.entities.Appointment;
import com.ehcare.ehcare.services.AppointmentService;
import com.ehcare.ehcare.util.DateUtil;

@RestController
@RequestMapping(path = "/appointment")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {

	@Autowired
	AppointmentService appointmentService;

	@Autowired
	DateUtil dateUtil;

	@GetMapping(path = "/{appointmentID}")
	public ResponseEntity<ResponseSuccess> getAppointment(@PathVariable int appointmentID) {
		Appointment appointment = appointmentService.getAppointment(appointmentID);
		return new ResponseEntity<>(new ResponseSuccess("Appointment fetched", true, appointment), HttpStatus.OK);
	}

	@GetMapping(path = "/doctor/{doctorID}")
	public ResponseEntity<ResponseSuccess> getAppointmentsByDoctor(@PathVariable int doctorID) {

		List<Appointment> appointments = appointmentService.getAllAppointmentsByDoctor(doctorID);
		return new ResponseEntity<>(new ResponseSuccess("Appointments fetched", true, appointments), HttpStatus.OK);
	}

	@GetMapping(path = "/date/{date}")
	public ResponseEntity<ResponseSuccess> getAppointmentsByAppointmentDate(@PathVariable("date") String date) {

		Date tempDate = dateUtil.parse(date);
		List<Appointment> appointments = appointmentService.getAllAppointmentsByAppointmentDate(tempDate);
		return new ResponseEntity<>(new ResponseSuccess("Appointments fetched", true, appointments), HttpStatus.OK);
	}

	@GetMapping(path = "/patient/{patientID}")
	public ResponseEntity<ResponseSuccess> getAppointmentsByPatient(@PathVariable int patientID) {

		List<Appointment> appointments = appointmentService.getAllAppointmentsByPatient(patientID);
		return new ResponseEntity<>(new ResponseSuccess("Appointments fetched", true, appointments), HttpStatus.OK);
	}

	@GetMapping(path = "/patient/date/{date}")
	public ResponseEntity<ResponseSuccess> getAppointmentByPatientAndAppointmentDate(HttpServletRequest request,
			@PathVariable("date") String date) {

		int patientID = (int) request.getAttribute("patientID");
		Date tempDate = dateUtil.parse(date);
		List<Appointment> appointments = appointmentService.getAllAppointmentsByPatientAndAppointmentDate(patientID,
				tempDate);
		return new ResponseEntity<>(new ResponseSuccess("Appointments fetched", true, appointments), HttpStatus.OK);
	}

	@GetMapping(path = "/doctor/date/{date}")
	public ResponseEntity<ResponseSuccess> getAppointmentByDoctorAndAppointmentDate(HttpServletRequest request,
			@PathVariable("date") String date) {

		int doctorID = (int) request.getAttribute("doctorID");
		Date tempDate = dateUtil.parse(date);
		List<Appointment> appointments = appointmentService.getAllAppointmentsByDoctorAndAppointmentDate(doctorID,
				tempDate);
		return new ResponseEntity<>(new ResponseSuccess("Appointments fetched", true, appointments), HttpStatus.OK);
	}

	@PostMapping("/{doctorID}")
	public ResponseEntity<ResponseSuccess> saveAppointment(@PathVariable int doctorID,
			@Valid @RequestBody Appointment appointment, HttpServletRequest request) {
		int patientID = (int) request.getAttribute("patientID");
		appointmentService.saveAppointment(patientID, doctorID, appointment);
		return new ResponseEntity<>(new ResponseSuccess("Appointment created", true, appointment), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<ResponseSuccess> updateAppointment(HttpServletRequest request,
			@Valid @RequestBody Appointment appointment) {
		Appointment updatedAppointment = appointmentService.updateAppointment(appointment.getAppointmentID(),
				appointment);
		return new ResponseEntity<>(new ResponseSuccess("Appointment updated", true, updatedAppointment),
				HttpStatus.OK);

	}

	@DeleteMapping(path = "/{appointmentID}")
	public ResponseEntity<ResponseSuccess> deleteAppointment(@PathVariable int appointmentID) {
		appointmentService.deleteAppointment(appointmentID);
		return new ResponseEntity<>(new ResponseSuccess("Appointment deleted", true), HttpStatus.OK);

	}

	@DeleteMapping(path = "/doctor/{doctorID}")
	public ResponseEntity<ResponseSuccess> deleteAppointmentsByDoctor(@PathVariable int doctorID) {
		appointmentService.deleteAppointmentByDoctor(doctorID);
		return new ResponseEntity<>(new ResponseSuccess("Appointments deleted", true), HttpStatus.OK);

	}

	@DeleteMapping(path = "/date/{date}")
	public ResponseEntity<ResponseSuccess> deleteAppointmentsByDoctor(@PathVariable("date") String date) {

		Date tempDate = dateUtil.parse(date);
		appointmentService.deleteAppointmentsByAppointmentDate(tempDate);
		return new ResponseEntity<>(new ResponseSuccess("Appointments deleted", true), HttpStatus.OK);
	}

}
