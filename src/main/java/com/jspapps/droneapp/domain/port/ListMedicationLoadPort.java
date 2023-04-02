package com.jspapps.droneapp.domain.port;

import com.jspapps.droneapp.domain.dto.PayloadDroneDTO;

import java.util.List;

public interface ListMedicationLoadPort {

    Long currentMedicationLoadByDrone(String droneId);

    List<PayloadDroneDTO> findMedicationLoadedByDrone(String drone);
}
