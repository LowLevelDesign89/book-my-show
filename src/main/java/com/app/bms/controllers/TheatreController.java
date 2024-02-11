package com.app.bms.controllers;


import com.app.bms.models.dto.request.TheatreRequestDTO;
import com.app.bms.models.entity.Theatre;
import com.app.bms.services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/theatres")
public class TheatreController {
    @Autowired
    private TheatreService theatreService;

    @PostMapping
    public ResponseEntity<Theatre> createTheatre(@RequestBody TheatreRequestDTO theatreRequestDTO) {
        return new ResponseEntity<>(theatreService.createTheatre(theatreRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{theatreId}")
    public ResponseEntity<Theatre> getTheatreDetails(@PathVariable Long theatreId) {
        return new ResponseEntity<>(theatreService.getTheatreDetails(theatreId), HttpStatus.OK);
    }
}
