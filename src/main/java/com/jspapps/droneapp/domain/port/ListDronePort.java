package com.jspapps.droneapp.domain.port;

import com.jspapps.droneapp.domain.dto.DroneDTO;

public interface ListDronePort {

    DroneDTO findDroneById(String droneId);
    DroneDTO findDroneBySerial(String droneSerial);
}
