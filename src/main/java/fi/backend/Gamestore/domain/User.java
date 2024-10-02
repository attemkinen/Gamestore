
package fi.backend.Gamestore.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity (name = "users")
public class User {
	@Id
	@Column(name = "username", nullable = false, unique = true)
	@NotBlank(message = "Username cannot be blank")
	@Size(min = 3, max = 40, message = "Username must be 5-40 characters")
	private String username;

	@Column(name = "password", nullable = false)
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
	private String passwordHash;

	@Column(name = "role", nullable = false)
    @NotBlank(message = "Role cannot be blank")
    @Pattern(regexp = "USER|ADMIN", message = "Role must be either USER or ADMIN")
	private String role;

	public User() {
	}

	public User(String username, String passwordHash, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}

