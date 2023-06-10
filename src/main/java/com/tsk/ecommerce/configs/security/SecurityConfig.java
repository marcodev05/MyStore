package com.tsk.ecommerce.configs.security;


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

import com.tsk.ecommerce.services.security.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private static final String[] SWAGGER = {
			"/v2/api-docs",
			"/swagger-resources",
			"/swagger-resources/**",
			"/configuration/ui",
			"/configuration/security",
			"/swagger-ui.html",
			"/webjars/**",
			// -- Swagger UI v3 (OpenAPI)
			"/v3/api-docs/**",
			"/swagger-ui/**"};
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
				.antMatchers("/api/v1/admin/**").hasRole("ADMIN")
				.antMatchers("/api/v1/seller/**").hasRole("SELLER")
				.antMatchers("/api/v1/user/**").hasRole("USER")
				.antMatchers("/api/v1/public/**").permitAll()
				.antMatchers(SWAGGER).permitAll()
				.and()
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public PasswordEncoder passWordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
