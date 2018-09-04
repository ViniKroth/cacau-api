package com.cacau.api.model.dto;

import java.util.Date;
import java.util.Optional;


public class UserRequestDTO {

	private Optional<String> id;

    private Optional<String> email;

    private Optional<String> password;

    private Optional<String> name;

	private Optional<Boolean> admin;

    public Optional<Date> creationDate;

    private boolean active;

    public UserRequestDTO() {
        this.active = true;
        this.admin = Optional.of(false);
    }

    public UserRequestDTO(UserRequestDTO user) {
        this.id = user.id != null ? user.id : Optional.ofNullable(null);
        this.email = user.email != null ? user.email : Optional.ofNullable(null);
        this.password = user.password != null ? user.password : Optional.ofNullable(null);
        this.name = user.name != null ? user.name : Optional.ofNullable(null);
        this.admin = user.admin != null ? user.admin : Optional.ofNullable(null);
        this.creationDate = user.creationDate != null ? user.creationDate : Optional.ofNullable(null);
        this.active = user.active;
    }

    public UserRequestDTO(Optional<String> id, Optional<String> email, Optional<String> password, Optional<String> name, Optional<Boolean> admin, Optional<Date> creationDate, boolean active) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.admin = admin;
		this.creationDate = creationDate;
		this.active = active;
	}

    public Optional<String> getId() {
        return id;
    }

    public void setId(Optional<String> id) { this.id = id; }

    public Optional<String> getEmail() {
		return email;
	}

	public void setEmail(Optional<String> email) {
		this.email = email;
	}

    public Optional<String> getPassword() {
        return password;
    }

    public void setPassword(Optional<String> password) {
        this.password = password;
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    public Optional<Boolean> isAdmin() {
        return admin;
    }

    public void setAdmin(Optional<Boolean> admin) {
        this.admin = admin;
    }

    public Optional<Date> getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Optional<Date> date) {
        this.creationDate= date;
    }

    public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "UserRequestDTO [email=" + email + ", id=" + id + ", active=" + active + ", admin=" + admin + ", name="
				+ name + ", password=" + password + ", creationDate=" + creationDate + "]";
	}

}
