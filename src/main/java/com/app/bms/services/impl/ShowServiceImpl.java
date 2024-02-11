package com.app.bms.services.impl;

import com.app.bms.exceptions.NotFoundException;
import com.app.bms.models.dto.request.ShowCreationRequestDTO;
import com.app.bms.models.dto.response.ShowResponseDTO;
import com.app.bms.models.entity.Hall;
import com.app.bms.models.entity.Movie;
import com.app.bms.models.entity.Show;
import com.app.bms.models.entity.ShowSeat;
import com.app.bms.repositories.HallRepository;
import com.app.bms.repositories.MovieRepository;
import com.app.bms.repositories.ShowRepository;
import com.app.bms.repositories.ShowSeatRepository;
import com.app.bms.services.AttributesBuilder;
import com.app.bms.services.ShowService;
import com.app.bms.utils.BookMyShowDTOEntityConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ShowServiceImpl implements ShowService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private AttributesBuilder attributesBuilder;

    @Override
    public ShowResponseDTO createShow(ShowCreationRequestDTO showCreationRequestDTO) {
        log.info("Show creation started for the request: {}", showCreationRequestDTO);
        Optional<Movie> movieOptional = movieRepository.findById(showCreationRequestDTO.getMovieId());
        if(movieOptional.isEmpty()) {
            log.error("Movie with ID: {} doesn't exist in the system", showCreationRequestDTO.getMovieId());
            throw new NotFoundException("Movie with ID:" +  showCreationRequestDTO.getMovieId() + " doesn't exist in the system");
        }

        Optional<Hall> hallOptional = hallRepository.findById(showCreationRequestDTO.getHallId());
        if(hallOptional.isEmpty()) {
            log.error("Hall with ID: {} doesn't exist in the system", showCreationRequestDTO.getHallId());
            throw new NotFoundException("Hall with ID: " + showCreationRequestDTO.getHallId() + " doesn't exist in the system");
        }
        Show show = new Show();
        show.setHall(hallOptional.get());
        show.setMovie(movieOptional.get());
        show.setStartTime(showCreationRequestDTO.getStartTime());
        show.setDuration(showCreationRequestDTO.getDuration());
        show.setCrearedAt(ZonedDateTime.now());
        show.setLastUpdatedAt(ZonedDateTime.now());
        showRepository.save(show);
        List<ShowSeat> showSeats = attributesBuilder.createShowSeats(hallOptional.get(), show);
        showSeatRepository.saveAll(showSeats);
        ShowResponseDTO showResponseDTO = BookMyShowDTOEntityConverter.convertToResponseDTO(show);
        showResponseDTO.setShowSeats(BookMyShowDTOEntityConverter.convertToShowSeatResponseDTOs(showSeats));

        log.info("Show creation finished: {}", showResponseDTO);
        return showResponseDTO;

    }
}
