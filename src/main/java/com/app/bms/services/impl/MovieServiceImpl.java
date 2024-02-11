package com.app.bms.services.impl;

import com.app.bms.exceptions.NotFoundException;
import com.app.bms.models.entity.Movie;
import com.app.bms.repositories.MovieRepository;
import com.app.bms.services.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie createMovie(Movie movie) {
        movie.setCrearedAt(ZonedDateTime.now());
        movie.setLastUpdatedAt(ZonedDateTime.now());
        return movieRepository.save(movie);
    }

    @Override
    public Movie getMovieDetails(Long movieId) {
        log.info("Movie retrieval started for movie ID: {}", movieId);
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if(movieOptional.isEmpty()) {
            log.error("Movie with ID: {} does not exist in the system", movieId);
            throw new NotFoundException("Movie with ID "+ movieId + " does not exist in the system");
        }
        log.info("Retrived movie details for the movie: {} is :{}", movieId, movieOptional.get());
        return movieOptional.get();
    }
}
