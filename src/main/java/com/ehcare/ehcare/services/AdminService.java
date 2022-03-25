package com.ehcare.ehcare.services;

import java.util.List;

import com.ehcare.ehcare.entities.Admin;


public interface AdminService {

	public Admin saveAdmin(Admin admin);
	public Admin updateAdmin(int adminID,Admin admin);
	public Admin getAdmin(int adminID);
	public Admin getAdminByAdminEmail(String adminEmail);
	public void deleteAdmin(int adminID);
	public List<Admin> getAllAdmins();
}
