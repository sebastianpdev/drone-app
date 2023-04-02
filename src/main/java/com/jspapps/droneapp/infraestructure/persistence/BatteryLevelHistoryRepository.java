package com.jspapps.droneapp.infraestructure.persistence;

import com.jspapps.droneapp.infraestructure.persistence.model.BatteryLevelHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatteryLevelHistoryRepository extends JpaRepository<BatteryLevelHistory, String> {

}
