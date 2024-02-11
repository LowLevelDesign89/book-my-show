package com.app.bms.models.entity;

import com.app.bms.models.enums.MovieFeature;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@SuperBuilder
@NoArgsConstructor
@Data
public class Hall extends BaseModel {
    private String name;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<MovieFeature> movieFeatures;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Seat> seats;
}
