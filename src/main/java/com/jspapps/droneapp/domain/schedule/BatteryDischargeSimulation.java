package com.jspapps.droneapp.domain.schedule;

import com.jspapps.droneapp.domain.dto.DroneDTO;
import com.jspapps.droneapp.domain.port.ListDronePort;
import com.jspapps.droneapp.domain.port.UpdateDronePort;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@AllArgsConstructor
@Configuration
public class BatteryDischargeSimulation {

    private final ListDronePort listDronePort;
    private final UpdateDronePort updateDronePort;

    @Scheduled(fixedRate = 60000) // 60 seconds
    public void simulateBatteryDischarge() {
        List<DroneDTO> droneList = listDronePort.findAll();
        if (!droneList.isEmpty()) {
            for (DroneDTO drone: droneList) {
                var battery = drone.getBattery() - 1L;
                if (battery < 0L) {
                    battery = 0L;
                }
                updateDronePort.updateDroneBatteryLevel(drone.getId(), battery);
            }
        }
    }
}
