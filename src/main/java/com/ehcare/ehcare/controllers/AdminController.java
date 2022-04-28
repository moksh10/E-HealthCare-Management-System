package com.ehcare.ehcare.controllers;

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
import com.ehcare.ehcare.entities.Admin;
import com.ehcare.ehcare.services.AdminService;

@RestController
@RequestMapping(path = "/admin")
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
public class AdminController {

	@Autowired
	AdminService adminService;

	@GetMapping(path = "/{adminID}")
	public ResponseEntity<ResponseSuccess> getAdmin(@PathVariable int adminID) {
		Admin admin = adminService.getAdmin(adminID);
		return new ResponseEntity<>(new ResponseSuccess("Admin fetched", true, admin), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<ResponseSuccess> getAdmins() {
		List<Admin> admins = adminService.getAllAdmins();
		return new ResponseEntity<>(new ResponseSuccess("Admins fetched", true, admins), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ResponseSuccess> saveAdmin(@Valid @RequestBody Admin admin) {

		Admin newAdmin = adminService.saveAdmin(admin);
		return new ResponseEntity<>(new ResponseSuccess("Admin created", true, newAdmin), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<ResponseSuccess> updateAdmin(HttpServletRequest request, @Valid @RequestBody Admin admin) {
		int adminID = (int) request.getAttribute("adminID");
		admin.setAdminID(adminID);
		Admin updatedAdmin = adminService.updateAdmin(admin.getAdminID(), admin);
		return new ResponseEntity<>(new ResponseSuccess("Admin updated", true, updatedAdmin), HttpStatus.OK);

	}

	@DeleteMapping(path = "/{adminID}")
	public ResponseEntity<ResponseSuccess> deleteAdmin(@PathVariable int adminID, HttpServletRequest request) {
		adminService.deleteAdmin(adminID);
		return new ResponseEntity<>(new ResponseSuccess("Admin deleted", true), HttpStatus.OK);

	}

}
