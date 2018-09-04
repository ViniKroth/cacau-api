package com.cacau.api.controller;

import com.cacau.api.model.dto.MediaRequestDTO;
import com.cacau.api.authentication.AuthorizationUtil;
import com.cacau.api.authentication.Roles;
import com.cacau.api.model.dto.SubmissionRequestDTO;
import com.cacau.api.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(("cacau/api/submissions"))
public class SubmissionController {
    @Autowired
    SubmissionService submissionService;
    @Autowired
    AuthorizationUtil auth;

    @PostMapping((""))
    public ResponseEntity<SubmissionRequestDTO> save(@Valid @RequestBody SubmissionRequestDTO submission, @RequestHeader(value = "Authorization") String acessToken) {
        try {
            auth.validateToken(acessToken, Roles.USER.toString());
            SubmissionRequestDTO createdSubmission = submissionService.create(submission);
            return new ResponseEntity<>(createdSubmission, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @GetMapping((""))
    public ResponseEntity<List<SubmissionRequestDTO>> getAll() {
        return new ResponseEntity<>(submissionService.getAll(), HttpStatus.OK);
    }

    @GetMapping(("/{id}"))
    public ResponseEntity<Optional<SubmissionRequestDTO>> get(@PathVariable String id, @RequestHeader(value = "Authorization") String acessToken) throws Exception {
        auth.validateToken(acessToken, Roles.USER.toString());
        Optional<SubmissionRequestDTO> submission = submissionService.get(id);
        if (submission.isPresent())
            return new ResponseEntity<>(submission, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping(("/{id}"))
    public ResponseEntity<SubmissionRequestDTO> update(@PathVariable String id,
            @Valid @RequestBody SubmissionRequestDTO submission, @RequestHeader(value = "Authorization") String acessToken) {
        SubmissionRequestDTO updateSubmission;
        try {
            auth.validateToken(acessToken, Roles.USER.toString());
            updateSubmission = submissionService.update(id, submission);
            return new ResponseEntity<>(updateSubmission, HttpStatus.OK);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }
    
    @DeleteMapping(("/{id}"))
    public HttpStatus delete(String id) {
        try {
            submissionService.delete(id);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        
    }
    
    @PostMapping(("/{id}/medias"))
    public ResponseEntity<MediaRequestDTO> insertMedia(@PathVariable String id, @Valid @RequestBody MediaRequestDTO media)  throws Exception{
        MediaRequestDTO returnMedia = submissionService.insertMedia(id, media);
        if (returnMedia != null) {
            return new ResponseEntity<>(media, HttpStatus.OK);
        } else return new ResponseEntity<>(media, HttpStatus.NOT_FOUND);
        
    }
}
