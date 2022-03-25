package com.ehcare.ehcare.services;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ehcare.ehcare.entities.Doctor;
import com.ehcare.ehcare.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	
	@Override
	@Transactional
	public Doctor saveDoctor(Doctor doctor) {
		Doctor checkDoctor=doctorRepository.findDoctorByDoctorEmail(doctor.getDoctorEmail());
		if(checkDoctor!=null)
			throw new RuntimeException();
		doctor.setPassword(bCryptPasswordEncoder.encode(doctor.getPassword()));
		return doctorRepository.save(doctor);
	}

	@Override
	@Transactional
	public Doctor updateDoctor(int doctorID, Doctor doctor) {
		Optional<Doctor> doctorToGet=doctorRepository.findById(doctorID);
		if(!doctorToGet.isPresent())
			throw new RuntimeException();
		Doctor doctorToUpdate=doctorToGet.get();
		doctorToUpdate.setDoctorEmail(doctor.getDoctorEmail());
		doctorToUpdate.setPassword(bCryptPasswordEncoder.encode(doctor.getPassword()));
		doctorToUpdate.setDoctorName(doctor.getDoctorName());
		doctorToUpdate.setDoctorContact(doctor.getDoctorContact());
		doctorToUpdate.setDoctorAddress(doctor.getDoctorAddress());
		doctorToUpdate.setShiftStartTime(doctor.getShiftStartTime());
		doctorToUpdate.setShiftEndTime(doctor.getShiftEndTime());
		doctorToUpdate.setSpecialistIn(doctor.getSpecialistIn());
		return doctorRepository.save(doctorToUpdate);
	}

	@Override
	@Transactional
	public Doctor getDoctor(int doctorID) {
		Optional<Doctor> doctorToGet=doctorRepository.findById(doctorID);
		if(!doctorToGet.isPresent())
			throw new RuntimeException();
		return doctorToGet.get();
	}

	@Override
	@Transactional
	public Doctor getDoctorByDoctorEmail(String doctorEmail) {
		if(doctorEmail.equals("invalid"))
			throw new RuntimeException();
		return doctorRepository.findDoctorByDoctorEmail(doctorEmail);
	}

	@Override
	@Transactional
	public void deleteDoctor(int doctorID) {
		Optional<Doctor> doctorToGet = doctorRepository.findById(doctorID);
		if(!doctorToGet.isPresent())
			throw new RuntimeException();
		Doctor doctorToDelete = doctorToGet.get();
	    doctorRepository.delete(doctorToDelete);
		
	}

	@Override
	@Transactional
	public List<Doctor> getAllDoctors() {
		List<Doctor> allDoctors=new LinkedList<Doctor>();
		Iterable<Doctor> doctors=doctorRepository.findAll();
		Iterator<Doctor> iterator=doctors.iterator();
		while(iterator.hasNext())
			allDoctors.add(iterator.next());
		return allDoctors;
	}

	@Override
	public void invalidateDoctorAccount(int doctorID) {
		Optional<Doctor> doctorToUpdate=doctorRepository.findById(doctorID);
		if(!doctorToUpdate.isPresent())
			throw new RuntimeException();
		doctorToUpdate.get().setDoctorEmail("invalid");
		doctorToUpdate.get().setPassword("xxxxxxxx1");
		doctorRepository.save(doctorToUpdate.get());
		
	}

}
