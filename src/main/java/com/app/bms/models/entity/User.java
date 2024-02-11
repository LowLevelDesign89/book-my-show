package com.app.bms.models.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity(name = "users")
@NoArgsConstructor
@SuperBuilder
@Data
public class User extends BaseModel {
    private String email;
}
