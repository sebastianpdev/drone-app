package com.jspapps.droneapp.infraestructure.persistence;

import com.jspapps.droneapp.infraestructure.persistence.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneRepository extends JpaRepository<Drone, String> {
}
