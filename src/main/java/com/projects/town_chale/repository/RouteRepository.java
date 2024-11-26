package com.projects.town_chale.repository;

import com.projects.town_chale.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    @Query("SELECT r FROM Route r " +
            "JOIN r.routeStops rs1 " +
            "JOIN r.routeStops rs2 " +
            "WHERE rs1.stop.id = :fromStopId " +
            "AND rs2.stop.id = :toStopId " +
            "AND rs1.stopOrder < rs2.stopOrder " +
            "ORDER BY rs1.stopOrder")
    Optional<List<Route>> findByRouteStops(@Param("fromStopId") Long fromStopId,
                                           @Param("toStopId") Long toStopId);

}
