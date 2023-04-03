package com.jspapps.droneapp.domain.usecase;

import com.jspapps.droneapp.application.exception.CustomRuntimeException;
import com.jspapps.droneapp.application.util.annotation.UseCase;
import com.jspapps.droneapp.domain.dto.DroneDTO;
import com.jspapps.droneapp.domain.port.ListDronePort;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@UseCase
public class ListDroneUseCase {

    private final ListDronePort listDronePort;

    public List<DroneDTO> findAllAvailable() {
        return listDronePort.findAllAvailable();
    }

    public DroneDTO findDroneById(String droneId) {
        var drone = listDronePort.findDroneById(droneId);
        if (drone == null) {
            throw new CustomRuntimeException("Drone not found.");
        }

        return drone;
    }
}
