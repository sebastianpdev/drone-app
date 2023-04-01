package com.jspapps.droneapp.infraestructure.persistence;

import com.jspapps.droneapp.infraestructure.persistence.model.DroneLoad;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneLoadRepository extends JpaRepository<DroneLoad, String> {

    @Query(value = "select sum(dl.medicationLoad) from DroneLoad dl where dl.drone.id = :drone")
    Long currentMedicationLoadByDrone(@Param("drone") String drone);

    @Modifying
    @Query(value = "delete from DroneLoad where drone.id = :drone")
    void cleanLoadDeliveredByDrone(@Param("drone") String droneId);

    @EntityGraph(attributePaths = {"medication", "drone"})
    List<DroneLoad> findAllByDrone_Id(String drone_id);
}
