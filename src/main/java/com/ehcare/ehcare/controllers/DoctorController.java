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
import com.ehcare.ehcare.entities.Doctor;
import com.ehcare.ehcare.services.DoctorService;

@RestController
@RequestMapping(path = "/doctor")
public class DoctorController {

	@Autowired
	DoctorService doctorService;

	@GetMapping(path = "/{doctorID}")
	public ResponseEntity<ResponseSuccess> getDoctor(@PathVariable int doctorID) {
		Doctor doctor = doctorService.getDoctor(doctorID);
		return new ResponseEntity<>(new ResponseSuccess("Doctor fetched", true, doctor), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<ResponseSuccess> getDoctors() {
		List<Doctor> doctors = doctorService.getAllDoctors();
		return new ResponseEntity<>(new ResponseSuccess("Doctors fetched", true, doctors), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ResponseSuccess> saveDoctor(@Valid @RequestBody Doctor doctor) {

		doctorService.saveDoctor(doctor);
		return new ResponseEntity<>(new ResponseSuccess("Doctor created", true, doctor), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<ResponseSuccess> updateDoctor(HttpServletRequest request, @Valid @RequestBody Doctor doctor) {
		int doctorID = (int) request.getAttribute("doctorID");
		doctor.setDoctorID(doctorID);
		Doctor updatedDoctor = doctorService.updateDoctor(doctor.getDoctorID(), doctor);
		return new ResponseEntity<>(new ResponseSuccess("Doctor updated", true, updatedDoctor), HttpStatus.OK);

	}

	@DeleteMapping(path = "/{doctorID}")
	public ResponseEntity<ResponseSuccess> deleteDoctor(@PathVariable int doctorID, HttpServletRequest request) {
		doctorService.deleteDoctor(doctorID);
		return new ResponseEntity<>(new ResponseSuccess("Doctor deleted", true), HttpStatus.OK);

	}

	@PutMapping(path = "/invalidate/{doctorID}")
	public ResponseEntity<ResponseSuccess> invalidateDoctor(@PathVariable int doctorID, HttpServletRequest request) {
		doctorService.invalidateDoctorAccount(doctorID);
		return new ResponseEntity<>(new ResponseSuccess("Doctor invalidated", true), HttpStatus.OK);

	}

}
