package com.projects.town_chale.repository;

import com.projects.town_chale.model.Route;
import com.projects.town_chale.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Optional<List<Schedule>> findAllByRoute(Route route);

}
