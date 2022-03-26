package com.ehcare.ehcare.exception;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ehcare.ehcare.dto.APIError;
import com.ehcare.ehcare.handlers.AppointmentNotFoundException;
import com.ehcare.ehcare.handlers.MedicalRecordNotFound;
import com.ehcare.ehcare.handlers.UserAlreadyExistsException;
import com.ehcare.ehcare.handlers.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<?> userAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException,WebRequest webRequest)
	{
		APIError apiError=new APIError();
		apiError.setMessage(userAlreadyExistsException.getMessage());
		apiError.setStatus(400);
		apiError.setTimestamp(Timestamp.from(Instant.now()));
		apiError.setError("Bad Request");
		return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
		
	}
//	@ExceptionHandler(UsernameNotFoundException.class)
//	public ResponseEntity<?> userNotFound(UsernameNotFoundException usernameNotFoundException,WebRequest webRequest)
//	{
//		APIError apiError=new APIError();
//		apiError.setMessage(usernameNotFoundException.getMessage());
//		apiError.setStatus(400);
//		apiError.setTimestamp(Timestamp.from(Instant.now()));
//		apiError.setError("Bad Request");
//		return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
//		
//	}
//	@ExceptionHandler(BadCredentialsException.class)
//	public ResponseEntity<?> authenticationException(BadCredentialsException badCredentialsException,WebRequest webRequest)
//	{
//		APIError apiError=new APIError();
//		apiError.setMessage(badCredentialsException.getMessage());
//		apiError.setStatus(401);
//		apiError.setTimestamp(Timestamp.from(Instant.now()));
//		apiError.setError("Bad Credentials");
//		return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
//		
//	}
//	@ExceptionHandler(AuthorizationException.class)
//	public ResponseEntity<?> authorizationException(AuthorizationException authorizationException,WebRequest webRequest)
//	{
//		APIError apiError=new APIError();
//		apiError.setMessage(authorizationException.getMessage());
//		apiError.setStatus(401);
//		apiError.setTimestamp(Timestamp.from(Instant.now()));
//		apiError.setError("Unauthorized");
//		return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
//		
//	}
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> userNotFoundException(UserNotFoundException userNotFoundException,WebRequest webRequest)
	{
		APIError apiError=new APIError();
		apiError.setMessage(userNotFoundException.getMessage());
		apiError.setStatus(404);
		apiError.setTimestamp(Timestamp.from(Instant.now()));
		apiError.setError("Not found");
		return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(AppointmentNotFoundException.class)
	public ResponseEntity<?> notFoundException(AppointmentNotFoundException appointmentNotFoundException,WebRequest webRequest)
	{
		APIError apiError=new APIError();
		apiError.setMessage(appointmentNotFoundException.getMessage());
		apiError.setStatus(404);
		apiError.setTimestamp(Timestamp.from(Instant.now()));
		apiError.setError("Not found");
		return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(MedicalRecordNotFound.class)
	public ResponseEntity<?> notFoundException(MedicalRecordNotFound medicalRecordNotFound,WebRequest webRequest)
	{
		APIError apiError=new APIError();
		apiError.setMessage(medicalRecordNotFound.getMessage());
		apiError.setStatus(404);
		apiError.setTimestamp(Timestamp.from(Instant.now()));
		apiError.setError("Not found");
		return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		APIError apiError=new APIError();
		apiError.setMessage("Path variable for resource missing");
		apiError.setStatus(400);
		apiError.setTimestamp(Timestamp.from(Instant.now()));
		apiError.setError("Bad request");
		return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
		
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		List<String> errors = new ArrayList<String>();
	    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
	        errors.add(error.getField() + ": " + error.getDefaultMessage());
	    }
	    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
	        errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
	    }
		APIError apiError=new APIError();
		apiError.setMessage("Validation error");
		apiError.setStatus(400);
		apiError.setTimestamp(Timestamp.from(Instant.now()));
		apiError.setError(errors.toString());
		return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);

	}
	public ResponseEntity<?> serverError(Exception exception)
	{
		
		APIError apiError=new APIError();
		apiError.setMessage("Server Error");
		apiError.setStatus(500);
		apiError.setTimestamp(Timestamp.from(Instant.now()));
		apiError.setError("Internal Server Error");
		return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
}
