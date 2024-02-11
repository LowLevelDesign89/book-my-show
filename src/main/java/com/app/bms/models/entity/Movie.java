package com.app.bms.models.entity;

import com.app.bms.models.enums.Genre;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@SuperBuilder
@NoArgsConstructor
@Data
public class Movie extends BaseModel{
    private String name;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Genre> genres;

    @ElementCollection
    private List<String> castAndCrew;
}
