package com.projects.town_chale.repository;

import com.projects.town_chale.model.SeatSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatScheduleRepository extends JpaRepository<SeatSchedule, Long> {
}
