package com.app.bms.controllers;

import com.app.bms.models.entity.Movie;
import com.app.bms.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.createMovie(movie), HttpStatus.CREATED);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> getMovieDetails(@PathVariable("movieId") Long movieId) {
        return new ResponseEntity<>(movieService.getMovieDetails(movieId), HttpStatus.OK);
    }
}
