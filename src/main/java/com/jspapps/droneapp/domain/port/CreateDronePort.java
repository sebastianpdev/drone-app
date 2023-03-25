package com.jspapps.droneapp.domain.port;

import com.jspapps.droneapp.domain.dto.CreateDrone;

public interface CreateDronePort {

    CreateDrone save(CreateDrone createDrone);
}
