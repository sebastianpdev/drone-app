package com.jspapps.droneapp.domain.port;

import com.jspapps.droneapp.domain.dto.RegisterPayloadDrone;

public interface CreatePayloadDronePort {

    RegisterPayloadDrone registerLoad(RegisterPayloadDrone droneLoad);
    void checkDroneToRegisterLoad(String droneId);
}
