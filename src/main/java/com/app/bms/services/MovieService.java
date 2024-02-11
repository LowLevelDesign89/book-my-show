package com.app.bms.services;

import com.app.bms.models.entity.Movie;

public interface MovieService {
    Movie createMovie(Movie movie);

    Movie getMovieDetails(Long movieId);
}
