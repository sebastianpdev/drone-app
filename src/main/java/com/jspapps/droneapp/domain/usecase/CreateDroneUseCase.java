package com.jspapps.droneapp.domain.usecase;

import com.jspapps.droneapp.application.exception.CustomRuntimeException;
import com.jspapps.droneapp.application.util.annotation.UseCase;
import com.jspapps.droneapp.domain.dto.CreateDrone;
import com.jspapps.droneapp.domain.port.CreateDronePort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCase
public class CreateDroneUseCase {

    private final CreateDronePort createDronePort;

    public void createDrone(CreateDrone drone) {
        if (drone.getId() != null) {
            throw new CustomRuntimeException("Drone id is not empty.");
        }
        createDronePort.save(drone);
    }


}
