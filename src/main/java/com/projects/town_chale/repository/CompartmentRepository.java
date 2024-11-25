package com.projects.town_chale.repository;

import com.projects.town_chale.model.Compartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompartmentRepository extends JpaRepository<Compartment, Long> {
}
