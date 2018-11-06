package com.cacau.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cacau.api.model.dto.VersionDTO;

@RestController
@RequestMapping(("cacau/api"))
public class PingController {

    @GetMapping(("/ping"))
    public ResponseEntity<VersionDTO> ping() {
        VersionDTO versionDTO = new VersionDTO();

        ResponseEntity<VersionDTO> response = new ResponseEntity<VersionDTO>(versionDTO, HttpStatus.OK);

        return response;
    }
}
