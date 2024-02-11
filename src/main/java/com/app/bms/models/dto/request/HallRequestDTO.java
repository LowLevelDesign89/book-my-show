package com.app.bms.models.dto.request;

import com.app.bms.models.enums.MovieFeature;
import lombok.Data;

import java.util.List;

@Data
public class HallRequestDTO {
    private String name;
    private List<MovieFeature> featureList;
    private int numSeatRows;
    private int numSeatCols;
}
