package com.tsk.ecommerce.entities.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tsk.ecommerce.entities.AuditEntity;
import com.tsk.ecommerce.entities.enumerations.ERole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Collection;

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

	private String username;

	@Size(min = 6, message = "password should have 6 letters at least")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	private String email;

	@Enumerated(value = EnumType.STRING)
	@ElementCollection(targetClass = ERole.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "role_entity")
	private Collection<ERole> roles = new ArrayList<>();

	private String firstName;

	private String lastName;

	private String phone;

	private String address1;

	private String address2;

	private String city;

}
