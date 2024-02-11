package com.app.bms.repositories;

import com.app.bms.models.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepository extends JpaRepository<Hall, Long> {
}
