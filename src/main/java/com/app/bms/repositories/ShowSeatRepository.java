package com.app.bms.repositories;

import com.app.bms.models.entity.Show;
import com.app.bms.models.entity.ShowSeat;
import com.app.bms.models.enums.SeatStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    List<ShowSeat> findByShowAndSeatStatus(Show show, SeatStatus seatStatus);

//    @Transactional
//    @Modifying
//    @Query("update ShowSeat ss set ss.seatStatus = ?2 where id = ?1")
//    int updateSeatStatus(Long showSeatId, SeatStatus seatStatus);
}
