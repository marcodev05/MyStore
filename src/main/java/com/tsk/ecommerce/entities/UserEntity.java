package com.tsk.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tsk.ecommerce.entities.enumerations.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	private String email;

	private String firstname;

	private String lastname;

	@Enumerated(value = EnumType.STRING)
	@ElementCollection(targetClass = ERole.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "role_entity")
	private Collection<ERole> roles = new ArrayList<>();

}
