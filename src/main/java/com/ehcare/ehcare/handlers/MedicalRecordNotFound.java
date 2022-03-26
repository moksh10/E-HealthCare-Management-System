package com.ehcare.ehcare.handlers;

@SuppressWarnings("serial")
public class MedicalRecordNotFound extends RuntimeException{
	
	public MedicalRecordNotFound() {
		super("Medical Record not found");
	}

}
