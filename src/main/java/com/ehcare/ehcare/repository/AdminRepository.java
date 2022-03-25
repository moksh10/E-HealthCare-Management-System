package com.ehcare.ehcare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ehcare.ehcare.entities.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer>{
	
	Admin findAdminByAdminEmail(String adminEmail);
}
