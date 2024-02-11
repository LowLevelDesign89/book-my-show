package com.app.bms.models.dto.request;

import lombok.Data;

@Data
public class ShowCreationRequestDTO {
    private Long movieId;
    private Long hallId;
    private Long startTime;
    private Long duration;
}
