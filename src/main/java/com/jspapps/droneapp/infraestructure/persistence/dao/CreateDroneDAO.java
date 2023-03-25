package com.jspapps.droneapp.infraestructure.persistence.dao;

import com.jspapps.droneapp.application.util.annotation.PersistenceAdapter;
import com.jspapps.droneapp.domain.dto.CreateDrone;
import com.jspapps.droneapp.domain.port.CreateDronePort;
import com.jspapps.droneapp.infraestructure.persistence.DroneRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
@PersistenceAdapter
public class CreateDroneDAO implements CreateDronePort {

    private final ModelMapper modelMapper;

    private final DroneRepository droneRepository;

    @Override
    public CreateDrone save(CreateDrone createDrone) {
        return null;
    }
}
