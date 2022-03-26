package com.ehcare.ehcare.controllers;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.ehcare.ehcare.entities.MedicalRecord;
import com.ehcare.ehcare.services.MedicalRecordService;

@RestController
@RequestMapping(path = "/medicalRecord")
public class MedicalRecordController {

	@Autowired
	MedicalRecordService medicalRecordService;

	@GetMapping(path = "/{medicalRecordID}")
	public ResponseEntity<ResponseSuccess> getMedicalRecord(@PathVariable int medicalRecordID) {
		MedicalRecord medicalRecord = medicalRecordService.getMedicalRecord(medicalRecordID);
		return new ResponseEntity<>(new ResponseSuccess("MedicalRecord fetched", true, medicalRecord), HttpStatus.OK);
	}

	@GetMapping(path = "/doctor/{doctorID}")
	public ResponseEntity<ResponseSuccess> getMedicalRecordsByDoctor(@PathVariable int doctorID) {

		List<MedicalRecord> medicalRecords = medicalRecordService.getAllMedicalRecordsByDoctor(doctorID);
		return new ResponseEntity<>(new ResponseSuccess("MedicalRecords fetched", true, medicalRecords), HttpStatus.OK);
	}

	@GetMapping(path = "/date/{date}")
	public ResponseEntity<ResponseSuccess> getMedicalRecordsByDate(
			@PathVariable("date") @DateTimeFormat(pattern = "dd/MM/yyyy") Date date) {

		List<MedicalRecord> medicalRecords = medicalRecordService.getAllMedicalRecordsByMedicalRecordDate(date);
		return new ResponseEntity<>(new ResponseSuccess("MedicalRecords fetched", true, medicalRecords), HttpStatus.OK);
	}

	@GetMapping(path = "/patient/{patientID}")
	public ResponseEntity<ResponseSuccess> getMedicalRecordsByPatient(@PathVariable int patientID) {

		List<MedicalRecord> medicalRecords = medicalRecordService.getAllMedicalRecordsByPatient(patientID);
		return new ResponseEntity<>(new ResponseSuccess("MedicalRecords fetched", true, medicalRecords), HttpStatus.OK);
	}

	@PostMapping("/{doctorID}")
	public ResponseEntity<ResponseSuccess> saveMedicalRecord(@PathVariable int doctorID,
			@Valid @RequestBody MedicalRecord medicalRecord, HttpServletRequest request) {
		int patientID = (int) request.getAttribute("patientID");
		medicalRecordService.saveMedicalRecord(patientID, doctorID, medicalRecord);
		return new ResponseEntity<>(new ResponseSuccess("MedicalRecord created", true), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<ResponseSuccess> updateMedicalRecord(HttpServletRequest request,
			@Valid @RequestBody MedicalRecord medicalRecord) {
		int medicalRecordID = (int) request.getAttribute("medicalRecordID");
		medicalRecord.setMedicalRecordID(medicalRecordID);
		MedicalRecord updatedMedicalRecord = medicalRecordService
				.updateMedicalRecord(medicalRecord.getMedicalRecordID(), medicalRecord);
		return new ResponseEntity<>(new ResponseSuccess("MedicalRecord updated", true, updatedMedicalRecord),
				HttpStatus.OK);

	}

	@DeleteMapping(path = "/{medicalRecordID}")
	public ResponseEntity<ResponseSuccess> deleteMedicalRecord(@PathVariable int medicalRecordID) {
		medicalRecordService.deleteMedicalRecord(medicalRecordID);
		return new ResponseEntity<>(new ResponseSuccess("MedicalRecord deleted", true), HttpStatus.NO_CONTENT);

	}

}