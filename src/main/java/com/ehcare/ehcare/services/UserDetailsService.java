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

		Client client = new Client();
		
		String[] usernameWithRole = username.split("#");
		username = usernameWithRole[0];
		String role = usernameWithRole[1];
		if (role.equals("ADMIN")) {
			Admin admin = adminRepository.findAdminByAdminEmail(username);
			if (admin != null) {
				client.setEmail(admin.getAdminEmail()+"#"+role);
				client.setPassword(admin.getPassword());
				client.setRole("ADMIN");
				CustomUserDetails userDetails = new CustomUserDetails(client);
				return userDetails;
			}
			throw new UsernameNotFoundException(username);
		}
		if (role.equals("PATIENT")) {
			Patient patient = patientRepository.findPatientByPatientEmail(username);
			if (patient != null) {
				client.setEmail(patient.getPatientEmail()+"#"+role);
				client.setPassword(patient.getPassword());
				client.setRole("PATIENT");
				CustomUserDetails userDetails = new CustomUserDetails(client);
				return userDetails;
			}
			throw new UsernameNotFoundException(username);
		}
		if (role.equals("DOCTOR")) {
			Doctor doctor = doctorRepository.findDoctorByDoctorEmail(username);
			if (doctor != null) {
				client.setEmail(doctor.getDoctorEmail()+"#"+role);
				client.setPassword(doctor.getPassword());
				client.setRole("DOCTOR");
				CustomUserDetails userDetails = new CustomUserDetails(client);
				return userDetails;
			}
			throw new UsernameNotFoundException(username);
		}
		throw new UsernameNotFoundException(username);
	}

}
