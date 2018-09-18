package com.cacau.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import com.cacau.api.authentication.AuthorizationUtil;
import com.cacau.api.authentication.Roles;
import com.cacau.api.model.Token;
import com.cacau.api.model.dto.UserRequestDTO;
import com.cacau.api.service.UserService;
import com.google.gson.Gson;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "cacau/api/users")
public class UserController {

    @Autowired
    AuthorizationUtil auth;
    @Autowired
    UserService userService;
    Gson gson = new Gson();

    @PostMapping((""))
    public ResponseEntity<UserRequestDTO> save(@Valid @RequestBody UserRequestDTO user) throws Exception {
        try {
            UserRequestDTO validatedUser = new UserRequestDTO(user);
            UserRequestDTO createdUser = userService.create(validatedUser);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping((""))
    public ResponseEntity<List<UserRequestDTO>> getAll(@RequestHeader(value = "Authorization") String acessToken) {
        try {
            auth.validateToken(acessToken, Roles.ADMIN.toString());
            return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
       
    }

    @GetMapping(("/{id}"))
    public ResponseEntity<Optional<UserRequestDTO>> get(@PathVariable String id, @RequestHeader(value = "Authorization") String acessToken) throws Exception {
        auth.validateToken(acessToken, Roles.USER.toString());
        Optional<UserRequestDTO> user = userService.get(id);
        if (user.isPresent())
            return new ResponseEntity<>(user, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(("/{id}"))
    public ResponseEntity<UserRequestDTO> put(@Valid @RequestBody UserRequestDTO user, @PathVariable String id, @RequestHeader(value = "Authorization") String acessToken) {
        try {
            auth.validateToken(acessToken, Roles.USER.toString());
            UserRequestDTO returnUser = userService.replace(user, id);
            return new ResponseEntity<>(returnUser, HttpStatus.OK);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PatchMapping(("/{id}"))
    public ResponseEntity<UserRequestDTO> update(@Valid @RequestBody UserRequestDTO user, @PathVariable String id, @RequestHeader(value = "Authorization") String acessToken) {
        try {
            auth.validateToken(acessToken, Roles.USER.toString());
            UserRequestDTO returnUser = userService.update(user, id);
            return new ResponseEntity<>(returnUser, HttpStatus.OK);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(("/{id}"))
    public ResponseEntity<UserRequestDTO> disableUser(@PathVariable String id, @RequestHeader(value = "Authorization") String acessToken) {
        try {
            auth.validateToken(acessToken, Roles.ADMIN.toString());
            userService.disable(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

}
