package com.tsk.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tsk.ecommerce.entities.AuditEntity;
import com.tsk.ecommerce.entities.Customer;
import com.tsk.ecommerce.entities.enumerations.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email"),
							@UniqueConstraint(columnNames = "username")})
public class UserEntity extends AuditEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@NotBlank(message = "username is required")
	private String username;

	@NotBlank(message = "password is required")
	@Size(min = 6, message = "password should have 6 letters at least")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@Email(message = "Invalid email")
	@NotBlank
	private String email;

	@OneToOne
	private Customer customer;

	@Enumerated(value = EnumType.STRING)
	@ElementCollection(targetClass = ERole.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "role_entity")
	private Collection<ERole> roles = new ArrayList<>();

}
