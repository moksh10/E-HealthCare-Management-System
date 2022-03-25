package com.ehcare.ehcare.services;

import java.util.List;

import com.ehcare.ehcare.entities.Doctor;

public interface DoctorService {

	public Doctor saveDoctor(Doctor doctor);
	public Doctor updateDoctor(int doctorID,Doctor doctor);
	public Doctor getDoctor(int doctorID);
	public Doctor getDoctorByDoctorEmail(String doctorEmail);
	public void deleteDoctor(int doctorID);
	public List<Doctor> getAllDoctors();
	public void invalidateDoctorAccount(int doctorID);
}
