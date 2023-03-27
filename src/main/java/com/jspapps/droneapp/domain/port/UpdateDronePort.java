package com.jspapps.droneapp.domain.port;

import com.jspapps.droneapp.application.util.constant.DroneState;

public interface UpdateDronePort {

    int updateDroneState(String droneId, DroneState droneState);
}
