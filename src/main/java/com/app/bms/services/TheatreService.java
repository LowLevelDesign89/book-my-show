package com.app.bms.services;

import com.app.bms.models.dto.request.TheatreRequestDTO;
import com.app.bms.models.entity.Theatre;

public interface TheatreService {
    Theatre createTheatre(TheatreRequestDTO theatreRequestDTO);

    Theatre getTheatreDetails(Long theatreId);
}
