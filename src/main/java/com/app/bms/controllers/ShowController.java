package com.app.bms.controllers;

import com.app.bms.models.dto.request.ShowCreationRequestDTO;
import com.app.bms.models.dto.response.ShowResponseDTO;
import com.app.bms.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shows")
public class ShowController {
    @Autowired
    private ShowService showService;

    @PostMapping
    public ResponseEntity<ShowResponseDTO> createShow(@RequestBody ShowCreationRequestDTO requestDTO) {
        return new ResponseEntity<>(showService.createShow(requestDTO), HttpStatus.CREATED);
    }
}
