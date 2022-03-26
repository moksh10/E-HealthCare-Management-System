package com.ehcare.ehcare.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehcare.ehcare.dto.ResponseSuccess;
import com.ehcare.ehcare.entities.Patient;
import com.ehcare.ehcare.services.PatientService;

@RestController
@RequestMapping(path = "/patient")
public class PatientController {

	@Autowired
	PatientService patientService;

	@GetMapping(path = "/{patientID}")
	public ResponseEntity<ResponseSuccess> getPatient(@PathVariable int patientID) {
		Patient patient = patientService.getPatient(patientID);
		return new ResponseEntity<>(new ResponseSuccess("Patient fetched", true, patient), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<ResponseSuccess> getPatients() {
		List<Patient> patients = patientService.getAllPatients();
		return new ResponseEntity<>(new ResponseSuccess("Patients fetched", true, patients), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ResponseSuccess> savePatient(@Valid @RequestBody Patient patient) {

		patientService.savePatient(patient);
		return new ResponseEntity<>(new ResponseSuccess("Patient created", true), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<ResponseSuccess> updatePatient(HttpServletRequest request,
			@Valid @RequestBody Patient patient) {
		int patientID = (int) request.getAttribute("patientID");
		patient.setPatientID(patientID);
		Patient updatedPatient = patientService.updatePatient(patient.getPatientID(), patient);
		return new ResponseEntity<>(new ResponseSuccess("Patient updated", true, updatedPatient), HttpStatus.OK);

	}

	@DeleteMapping(path = "/{patientID}")
	public ResponseEntity<ResponseSuccess> deletePatient(@PathVariable int patientID, HttpServletRequest request) {
		patientService.deletePatient(patientID);
		return new ResponseEntity<>(new ResponseSuccess("Patient deleted", true), HttpStatus.NO_CONTENT);

	}

}
