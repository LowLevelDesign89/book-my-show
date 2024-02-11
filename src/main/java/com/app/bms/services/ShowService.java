package com.app.bms.services;

import com.app.bms.models.dto.request.ShowCreationRequestDTO;
import com.app.bms.models.dto.response.ShowResponseDTO;

public interface ShowService {
    ShowResponseDTO createShow(ShowCreationRequestDTO showCreationRequestDTO);
}
