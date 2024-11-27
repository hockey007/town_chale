package com.projects.town_chale.repository;

import com.projects.town_chale.model.SeatScheduleSegment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatScheduleSegmentRepository extends JpaRepository<SeatScheduleSegment, Long> {
    @Query("""
        SELECT COUNT(DISTINCT sss.seatSchedule.id)
        FROM SeatScheduleSegment sss
        WHERE sss.seatSchedule.schedule.id = :scheduleId
          AND EXISTS (
              SELECT 1
              FROM SeatScheduleSegment conflict
              WHERE conflict.seatSchedule.id = sss.seatSchedule.id
                AND conflict.fromStop.id < :toStopId
                AND conflict.toStop.id > :fromStopId
          )
    """)
    Integer countNonConflictingSeats(
            @Param("scheduleId") Long scheduleId,
            @Param("fromStopId") Long fromStopId,
            @Param("toStopId") Long toStopId
    );
}
