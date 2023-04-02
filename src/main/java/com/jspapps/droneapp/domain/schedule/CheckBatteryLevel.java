package com.jspapps.droneapp.domain.schedule;

import com.jspapps.droneapp.domain.dto.DroneDTO;
import com.jspapps.droneapp.domain.dto.RegisterBatteryLevelHistory;
import com.jspapps.droneapp.domain.port.CreateBatteryLevelHistoryPort;
import com.jspapps.droneapp.domain.port.ListDronePort;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Configuration
public class CheckBatteryLevel {

    private final ListDronePort listDronePort;
    private final CreateBatteryLevelHistoryPort createBatteryLevelHistoryPort;

    @Scheduled(fixedDelay = 20000)
    public void checkBatteryLevel() {
        List<DroneDTO> droneList = listDronePort.findAll();
        if (!droneList.isEmpty()) {
            for (DroneDTO drone : droneList) {
                createBatteryLevelHistoryPort.createBatteryLevelLog(RegisterBatteryLevelHistory.builder()
                                .serialDrone(drone.getSerial())
                                .batteryLevel(drone.getBattery())
                                .createdDate(Instant.now())
                                .build());
            }
        }
    }
}
