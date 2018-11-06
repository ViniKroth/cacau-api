package com.cacau.api.model.dto;

import java.util.Date;
import java.util.Optional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class UserMongoDTO {
	@NotBlank
	@NotNull
	@Indexed(unique = true)
	private String email;
	@Id
	private String id;

	private boolean active;

	private boolean admin;

	private String name;

	private String password;

	public Date creationDate;

	public UserMongoDTO(String email, String id, boolean active, boolean admin, String name, String password, Date date) {
		this.email = email;
		this.id = id;
		this.active = active;
		this.admin = admin;
		this.name = name;
		this.password = password;
		this.creationDate = date;
	}

	public UserMongoDTO() {

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public UserRequestDTO toRequestDTO() {
		UserRequestDTO user = new UserRequestDTO(Optional.of(id), Optional.of(email), Optional.of(password), Optional.of(name), Optional.of(admin), Optional.of(creationDate), active);
		return user;
	}

	@Override
	public String toString() {
		return "UserMongoDTO [email=" + email + ", id=" + id + ", active=" + active + ", admin=" + admin + ", name="
				+ name + ", password=" + password + ", creationDate=" + creationDate + "]";
	}

}
