package com.jspapps.droneapp.infraestructure.persistence.dao;

import com.jspapps.droneapp.application.util.annotation.PersistenceAdapter;
import com.jspapps.droneapp.domain.dto.PayloadDroneDTO;
import com.jspapps.droneapp.domain.port.ListMedicationLoadPort;
import com.jspapps.droneapp.infraestructure.persistence.DroneLoadRepository;
import com.jspapps.droneapp.infraestructure.persistence.model.DroneLoad;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@AllArgsConstructor
@PersistenceAdapter
public class ListMedicationLoadDAO implements ListMedicationLoadPort {

    private final Logger logger = Logger.getLogger(ListMedicationLoadDAO.class.getName());
    private final ModelMapper modelMapper;
    private final DroneLoadRepository droneLoadRepository;

    @Override
    public Long currentMedicationLoadByDrone(String droneId) {
        var currentWeight = droneLoadRepository.currentMedicationLoadByDrone(droneId);
        if (currentWeight == null) {
            return 0L;
        }
        return currentWeight;
    }

    @Override
    public List<PayloadDroneDTO> findMedicationLoadedByDrone(String drone) {
        var payload = droneLoadRepository.findAllByDrone_Id(drone);
        if (payload.isEmpty()) {
            return Collections.emptyList();
        }

        List<PayloadDroneDTO> payloadList = new ArrayList<>();
        var groupingByMedication = payload.stream().collect(Collectors.groupingBy(e -> e.getMedication().getId()));
        groupingByMedication.forEach((medication, mPayloadList) -> {
            var payloadTemp = mPayloadList.get(0);
            payloadList.add(PayloadDroneDTO.builder()
                            .droneId(payloadTemp.getDrone().getId())
                            .droneSerial(payloadTemp.getDrone().getSerial())
                            .medicationId(medication)
                            .medicationName(payloadTemp.getMedication().getName())
                            .medicationLoad(mPayloadList.stream().mapToLong(DroneLoad::getMedicationLoad).sum())
                            .quantity(mPayloadList.stream().mapToLong(DroneLoad::getQuantity).sum())
                            .build()
            );
        });

        return payloadList;
    }
}
