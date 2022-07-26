package com.ehcare.ehcare.controllers;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehcare.ehcare.dto.AuthenticationRequest;
import com.ehcare.ehcare.dto.ResponseSuccess;
import com.ehcare.ehcare.handlers.AuthorizationException;
import com.ehcare.ehcare.services.UserDetailsService;
import com.ehcare.ehcare.util.JwtUtil;
import com.ehcare.ehcare.util.UserInfo;

@RestController
@CrossOrigin(origins = "https://the12thplayer-wvehgh.web.app",allowCredentials = "true")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping("/auth")
	public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest authenticationRequest,
			HttpServletResponse response) throws Exception {

//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//					authenticationRequest.getEmail() + "#" + authenticationRequest.getRole(),
//					authenticationRequest.getPassword()));
//		} catch (BadCredentialsException e) {
//			throw new BadCredentialsException("Incorrect email or password");
//		}

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getEmail() + "#" + authenticationRequest.getRole());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		Cookie cookie = new Cookie("jwt", jwt);
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		cookie.setMaxAge(60 * 60 * 6);
//		ResponseCookie responseCookie=new ResponseCookie();
//		responseCookie.
	//	response.addCookie(cookie);
		response.setHeader("Set-Cookie", "jwt="+jwt+"; HttpOnly; Secure; SameSite=none");
		return new ResponseEntity<>(new ResponseSuccess("Logged In", true), HttpStatus.OK);
	}

	@RequestMapping("/authFailure")
	public void authFailure() {

		throw new AuthorizationException();
	}

	@GetMapping("/userInfo")
	public ResponseEntity<?> userInfo(HttpServletRequest request) {

		int userID = (int) request.getAttribute("userID");
		String role = (String) request.getAttribute("role");
		String username = (String) request.getAttribute("username");
		return ResponseEntity.ok(new ResponseSuccess("User fetched", true, new UserInfo(userID, role, username)));
	}

	@GetMapping("/isAuth")
	public ResponseEntity<?> isAuth() {

		return ResponseEntity.ok(new ResponseSuccess("Authorized", true));
	}

	@PostMapping("/logoutUser")
	public ResponseEntity<?> logout(HttpServletResponse response) {

		Cookie cookie = new Cookie("jwt", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return ResponseEntity.ok(new ResponseSuccess("Logged Out", true));
	}
}
