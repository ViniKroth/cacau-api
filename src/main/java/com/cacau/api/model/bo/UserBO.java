package com.cacau.api.model.bo;

import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cacau.api.model.dto.UserMongoDTO;
import com.cacau.api.model.dto.UserRequestDTO;
import com.cacau.api.validator.Validator;
import com.cacau.api.exception.PersistencyException;
import com.cacau.api.exception.ValidationException;

public class UserBO {

	private String id;

	private String email;

	private String password;

	private String name;

	private boolean admin;

	public Date creationDate;

	private boolean active;

	Validator validator = new Validator();

	public UserBO(UserRequestDTO user) throws PersistencyException, ValidationException {
		setActive(user.isActive());
		setEmail(user.getEmail().isPresent() ? user.getEmail().get(): " ");
		setPassword(user.getPassword().isPresent() ? user.getPassword().get(): " ");
		setName(user.getName().isPresent() ? user.getName().get(): " ");
		setAdmin(user.isAdmin().get());
		creationDate = new Date();
	}

	public UserBO(UserMongoDTO user) throws ValidationException {
		setId(user.getId());
		setEmail(user.getEmail());
		setName(user.getName());
		setPassword(user.getPassword());
		setAdmin(user.isAdmin());
		setCreationDate(user.getCreationDate());
		setActive(user.isActive());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

    public void setEmail(String email) throws ValidationException {
        if (validator.validateEmail(email) && email != null)
            this.email = email.toLowerCase();
        else
            throw new ValidationException("INVALID_EMAIL_INFORMED");
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
    
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


    public void setName(String name) throws ValidationException {
        if (validator.validateStringCharacter(name, 8, 50) && name != null)
            this.name = name;
        else
            throw new ValidationException("NAME_TOO_BIG_OR_TOO_SMALL");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws ValidationException {
        if (validator.validatePassword(password, 8, 24) && password != null) {
            BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
            this.password = bCrypt.encode(password);
        } else
            throw new ValidationException("PASSWORD_TOO_BIG_OR_TOO_SMALL");
    }

    public void updateWith(UserRequestDTO user) throws ValidationException {
        setEmail(user.getEmail().isPresent() ? user.getEmail().get() : this.email);
        setPassword(user.getPassword().isPresent() ? user.getPassword().get() : this.password);
        setName(user.getName().isPresent() ? user.getName().get():this.name);
        setAdmin(user.isAdmin().isPresent() ? user.isAdmin().get():this.admin);
    }
    
    public UserMongoDTO toMongoDTO() {
        UserMongoDTO user = new UserMongoDTO(email, id, active, admin, name, password, creationDate);
        return user;
        
    }
    
    

}
