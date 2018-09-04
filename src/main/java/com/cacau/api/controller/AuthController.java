package com.cacau.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.cacau.api.authentication.AuthorizationUtil;
import com.cacau.api.authentication.JWT;
import com.cacau.api.model.Token;
import com.cacau.api.model.dto.UserAuthorizationInfoDTO;
import com.cacau.api.model.dto.UserMongoDTO;

@RestController
@RequestMapping(path = "cacau/api")
public class AuthController {
    @Autowired
    AuthorizationUtil auth;

    @PostMapping(("/login"))
    public Token login(@RequestBody UserAuthorizationInfoDTO loggedUser,
            @RequestHeader(value = "Authorization") String clientHeader) {
        try {
            auth.validateClient(clientHeader);
            UserMongoDTO validatedUser = auth.validateLoggedUser(loggedUser);
            String scope = validatedUser.isAdmin() ? "ADMIN" : "USER";
            String jwt = JWT.createJWT(validatedUser.getId(), validatedUser.getEmail(), scope);
            Token token = new Token(validatedUser, jwt);
            return token;
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, e.getMessage());

        }
    }
}
