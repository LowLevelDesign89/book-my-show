package com.app.bms.models.dto.request;

import com.app.bms.models.entity.Address;
import lombok.Data;

import java.util.List;

@Data
public class TheatreRequestDTO {
    private String name;
    private Address address;
    private List<HallRequestDTO> halls;
}
