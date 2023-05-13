package com.tsk.ecommerce.entities.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tsk.ecommerce.entities.Customer;
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
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email"),
							@UniqueConstraint(columnNames = "username")})
public class UserEntity {
	
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

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(
					name = "user_id"),
			inverseJoinColumns = @JoinColumn(
					name = "role_id")
	)
	private Collection<RoleEntity> roles = new ArrayList<>();

	@CreatedDate
	private Date createdAt;

	@LastModifiedDate
	private Date modifiedAt;
}
