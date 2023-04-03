package com.jspapps.droneapp.infraestructure.persistence;

import com.jspapps.droneapp.application.util.constant.DroneState;
import com.jspapps.droneapp.infraestructure.persistence.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<Drone, String> {

    Optional<Drone> findDroneBySerial(@NotNull @Size(max = 100) String serial);

    @Transactional
    @Modifying
    @Query(value = "update Drone set state = :state where id = :droneId")
    int updateDroneState(@Param("droneId") String droneId, @Param("state") DroneState droneState);

    @Transactional
    @Modifying
    @Query(value = "update Drone set battery = :batteryLevel where id = :droneId")
    int updateBatteryLevel(@Param("batteryLevel") Long batteryLevel, @Param("droneId") String droneId);

    List<Drone> findAllByState(DroneState state);
}
