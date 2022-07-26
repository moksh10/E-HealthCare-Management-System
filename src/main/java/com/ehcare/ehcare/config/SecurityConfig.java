package com.ehcare.ehcare.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.ehcare.ehcare.filter.JwtRequestFilter;
import com.ehcare.ehcare.services.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity security) throws Exception {
		
//		security.authorizeHttpRequests().anyRequest().permitAll();
//		security.csrf().disable();
		security.cors().and().csrf().disable().authorizeRequests()
    	.antMatchers("/authFailure").permitAll()
    	.antMatchers(HttpMethod.POST,"/auth").permitAll()
    	.antMatchers(HttpMethod.POST,"/patient").permitAll()
    	.antMatchers(HttpMethod.GET,"/doctor/**").permitAll()
    	.antMatchers(HttpMethod.POST,"/appointment/**").hasAnyAuthority("ADMIN","PATIENT")
    	.antMatchers("/appointment/**").hasAnyAuthority("ADMIN","DOCTOR","PATIENT")
    	.antMatchers(HttpMethod.GET,"/medicalRecord/**").hasAnyAuthority("ADMIN","DOCTOR","PATIENT")
    	.antMatchers("/medicalRecord/**").hasAnyAuthority("ADMIN","DOCTOR")
    	.antMatchers(HttpMethod.GET,"/doctor/**").hasAnyAuthority("ADMIN","DOCTOR","PATIENT")
    	.antMatchers("/doctor/**").hasAnyAuthority("ADMIN","DOCTOR")
    	.antMatchers(HttpMethod.GET,"/patient/**").hasAnyAuthority("ADMIN","DOCTOR","PATIENT")
    	.antMatchers("/patient/**").hasAnyAuthority("ADMIN","PATIENT")
    	.antMatchers("/admin/**").hasAuthority("ADMIN")
		.antMatchers("/userInfo").authenticated()
    	.antMatchers("/isAuth").authenticated()
    	.antMatchers("/logoutUser").authenticated()
    	.and().formLogin().and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	security.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    	security.exceptionHandling()
        .authenticationEntryPoint(
            (request, response, ex) -> {
                response.sendRedirect("/authFailure");
            }
        );
	}
	
}