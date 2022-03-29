package com.ehcare.ehcare.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


@Entity
@Table(name="admin")
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="admin_id")
	private int adminID;
	
	@Column(name="admin_email")
	@Email(message = "Not a valid email")
	@NotEmpty(message = "Admin email required")
	@Size(max=200,message = "Email length exceeded: 200")
	@NotNull
	private String adminEmail;
	
	@Column(name="password")
	@Size(min=8,message = "Password must be minimum of 8 characters")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@Column(name="admin_name")
	@NotEmpty(message = "Admin name cannot be empty")
	@Size(max=200,message = "Admin name length exceeded: 200")
	private String adminName;
	
	@Column(name="admin_contact")
	@NotEmpty(message = "Admin contact cannot be empty")
	@Size(max=12,message = "Admin name length exceeded: 12")
	private String adminContact;
	

	public Admin() {
		
	}


	public Admin(String adminEmail,String adminName,String adminContact) {
		this.adminEmail = adminEmail;
		this.adminName = adminName;
		this.adminContact = adminContact;
	}


	public int getAdminID() {
		return adminID;
	}


	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}


	public String getAdminEmail() {
		return adminEmail;
	}


	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getAdminName() {
		return adminName;
	}


	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}


	public String getAdminContact() {
		return adminContact;
	}


	public void setAdminContact(String adminContact) {
		this.adminContact = adminContact;
	}


	@Override
	public String toString() {
		return "Admin [adminID=" + adminID + ", adminEmail=" + adminEmail + ", password=" + password + ", adminName="
				+ adminName + ", adminContact=" + adminContact + "]";
	}
	
	
	
	
}
