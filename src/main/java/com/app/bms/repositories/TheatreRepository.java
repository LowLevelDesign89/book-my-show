package com.app.bms.repositories;

import com.app.bms.models.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {
}
