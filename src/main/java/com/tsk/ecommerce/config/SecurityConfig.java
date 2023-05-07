package com.tsk.ecommerce.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tsk.ecommerce.config.jwt.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	JwtFilter jwtFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				.httpBasic().disable()
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/seller/**").hasRole("SELLER")
				.antMatchers("/user/**").hasRole("USER")
				.antMatchers("/public/**").permitAll()
				.antMatchers("/v2/api-docs",
						"/swagger-resources",
						"/swagger-resources/**",
						"/configuration/ui",
						"/configuration/security",
						"/swagger-ui.html",
						"/webjars/**",
						// -- Swagger UI v3 (OpenAPI)
						"/v3/api-docs/**",
						"/swagger-ui/**").permitAll()
				.and()
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	
	
	@Bean
	public PasswordEncoder passWordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
}
