package com.app.bms.models.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Data
@NoArgsConstructor
public class Seat extends BaseModel {
    private Integer rowNum;
    private Integer colNum;
}
