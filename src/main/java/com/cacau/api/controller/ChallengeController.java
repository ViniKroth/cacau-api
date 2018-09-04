package com.cacau.api.controller;

import com.cacau.api.authentication.AuthorizationUtil;
import com.cacau.api.authentication.Roles;
import com.cacau.api.model.dto.ChallengeRequestDTO;
import com.cacau.api.model.dto.PrizeRequestDTO;
import com.cacau.api.service.ChallengeService;
import com.cacau.api.service.PrizeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(("cacau/api/challenges"))
public class ChallengeController {
    @Autowired
    AuthorizationUtil auth;
    @Autowired
    ChallengeService challengeService;
    @Autowired
    PrizeService prizeService;

    @PostMapping((""))
    public ResponseEntity<ChallengeRequestDTO> create(@Valid @RequestBody ChallengeRequestDTO challenge,
            @RequestHeader(value = "Authorization") String acessToken) throws Exception {
        auth.validateToken(acessToken, Roles.ADMIN.toString());
        ChallengeRequestDTO challengeRequest = challengeService.create(challenge);
        return new ResponseEntity<>(challengeRequest, HttpStatus.CREATED);
    }

    @GetMapping((""))
    public ResponseEntity<List<ChallengeRequestDTO>> getAll(@RequestHeader(value = "Authorization") String acessToken)
            throws Exception {
        auth.validateToken(acessToken, Roles.USER.toString());
        return new ResponseEntity<>(challengeService.getAll(), HttpStatus.OK);
    }

    @GetMapping(("/{id}"))
    public ResponseEntity<Optional<ChallengeRequestDTO>> get(@PathVariable String id,
            @RequestHeader(value = "Authorization") String acessToken) throws Exception {
        auth.validateToken(acessToken, Roles.USER.toString());
        Optional<ChallengeRequestDTO> challenge = challengeService.get(id);
        if (challenge.isPresent()) {
            return new ResponseEntity<>(challenge, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(("/{id}"))
    public ResponseEntity<ChallengeRequestDTO> disable(@PathVariable String id,
            @RequestHeader(value = "Authorization") String acessToken) {
        try {
            auth.validateToken(acessToken, Roles.ADMIN.toString());
            challengeService.disable(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PatchMapping(("/{id}"))
    public ResponseEntity<ChallengeRequestDTO> update(@PathVariable String id,
            @RequestBody @Valid ChallengeRequestDTO challenge,
            @RequestHeader(value = "Authorization") String acessToken) {
        try {
            auth.validateToken(acessToken, Roles.ADMIN.toString());
            ChallengeRequestDTO createdUser;
            createdUser = challengeService.update(id, challenge);
            return new ResponseEntity<ChallengeRequestDTO>(createdUser, HttpStatus.OK);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
    // Prize
    @PostMapping(("/{id}/prizes"))
    public ResponseEntity<PrizeRequestDTO> createPrize(@Valid @RequestBody PrizeRequestDTO prize, @PathVariable String id) throws Exception {
        PrizeRequestDTO createdPrize = prizeService.create(prize);
        return new ResponseEntity<>(createdPrize, HttpStatus.CREATED);
    }
    
    @GetMapping(("/{id}/prizes"))
    public ResponseEntity<List<PrizeRequestDTO>> getAllPrizes() {
        return new ResponseEntity<>(prizeService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping(("/prizes/{id}"))
    public ResponseEntity<Optional<PrizeRequestDTO>> getPrize(@PathVariable String id) throws Exception {
        Optional<PrizeRequestDTO> prize = prizeService.get(id);
        if (prize.isPresent())
            return new ResponseEntity<>(prize, HttpStatus.OK);
        else
            return new ResponseEntity<>(prize, HttpStatus.NOT_FOUND);
    }
}
