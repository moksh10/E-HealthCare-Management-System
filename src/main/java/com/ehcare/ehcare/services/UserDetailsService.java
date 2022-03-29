package com.ehcare.ehcare.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ehcare.ehcare.entities.Admin;
import com.ehcare.ehcare.entities.Doctor;
import com.ehcare.ehcare.entities.Patient;
import com.ehcare.ehcare.repository.AdminRepository;
import com.ehcare.ehcare.repository.DoctorRepository;
import com.ehcare.ehcare.repository.PatientRepository;
import com.ehcare.ehcare.util.Client;
import com.ehcare.ehcare.util.CustomUserDetails;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	DoctorRepository doctorRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		Client client = new Client();
		Admin admin=adminRepository.findAdminByAdminEmail(username);
		if(admin!=null)
		{
			client.setEmail(admin.getAdminEmail());
			client.setPassword(admin.getPassword());
			client.setRole("ADMIN");
			CustomUserDetails userDetails = new CustomUserDetails(client);
			return userDetails;
		}
		admin=null;
		Patient patient =patientRepository.findPatientByPatientEmail(username);
		if(patient!=null)
		{
			client.setEmail(patient.getPatientEmail());
			client.setPassword(patient.getPassword());
			client.setRole("PATIENT");
			CustomUserDetails userDetails = new CustomUserDetails(client);
			return userDetails;
		}
		patient=null;
		Doctor doctor=doctorRepository.findDoctorByDoctorEmail(username);
		if(doctor!=null)
		{
			client.setEmail(doctor.getDoctorEmail());
			client.setPassword(doctor.getPassword());
			client.setRole("DOCTOR");
			CustomUserDetails userDetails = new CustomUserDetails(client);
			return userDetails;
		}
		throw new UsernameNotFoundException(username);
		
	}

}
