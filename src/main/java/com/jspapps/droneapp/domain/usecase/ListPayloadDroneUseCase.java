package com.jspapps.droneapp.domain.usecase;

import com.jspapps.droneapp.application.util.annotation.UseCase;
import com.jspapps.droneapp.domain.dto.PayloadDroneDTO;
import com.jspapps.droneapp.domain.port.ListMedicationLoadPort;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@UseCase
public class ListPayloadDroneUseCase {

    private final ListMedicationLoadPort listMedicationLoadPort;

    public List<PayloadDroneDTO> findMedicationLoadedByDrone(String drone) {
        return listMedicationLoadPort.findMedicationLoadedByDrone(drone);
    }


}
