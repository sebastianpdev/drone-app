package com.jspapps.droneapp.domain.port;

import com.jspapps.droneapp.domain.dto.RegisterLoadDrone;

public interface CreateLoadDronePort {

    RegisterLoadDrone registerLoad(RegisterLoadDrone droneLoad);
    void checkDroneToRegisterLoad(String droneId);
}
