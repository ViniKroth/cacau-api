package com.cacau.api.model.dto;

import java.util.Optional;

import org.springframework.data.annotation.Id;

public class UserAuthorizationInfoDTO {

    private Optional<String> email;
    private Optional<String> password;
    @Id
    private String id;

    public Optional<String> getEmail() {
        if (email.isPresent()) {
            String optionalEmail = email.get().toLowerCase();
            return Optional.of(optionalEmail);
        }
        return email;
    }

    public Optional<String> getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

}
