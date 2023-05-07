package com.tsk.ecommerce.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tsk.ecommerce.entities.auth.UserEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
public class Customer implements Serializable {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	
	@NotBlank(message = "Name is required")
	private String firstName;
	
	private String lastName;
	
	@Email(message = "email is invalid")
	@NotBlank(message = "email is required")
	private String email;
	
	private String phone;
	
	@NotBlank(message = "Address is required")
	private String address1;

	private String address2;
	
	@NotBlank(message = "City is required")
	@NotNull
	private String city;

	@OneToOne(mappedBy = "customer")
	private UserEntity user;

	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private Collection<Orders> orders;
}
