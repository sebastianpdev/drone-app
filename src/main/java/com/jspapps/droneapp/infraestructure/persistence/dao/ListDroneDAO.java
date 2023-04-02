package com.jspapps.droneapp.infraestructure.persistence.dao;

import com.jspapps.droneapp.application.util.annotation.PersistenceAdapter;
import com.jspapps.droneapp.domain.dto.DroneDTO;
import com.jspapps.droneapp.domain.port.ListDronePort;
import com.jspapps.droneapp.infraestructure.persistence.DroneRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@PersistenceAdapter
public class ListDroneDAO implements ListDronePort {

    private final ModelMapper modelMapper;
    private final DroneRepository droneRepository;

    @Override
    public DroneDTO findDroneById(String droneId) {
        return modelMapper.map(droneRepository.findById(droneId), DroneDTO.class);
    }

    @Override
    public DroneDTO findDroneBySerial(String droneSerial) {
        var drone = droneRepository.findDroneBySerial(droneSerial).orElse(null);
        return modelMapper.map(drone, DroneDTO.class);
    }

    @Override
    public List<DroneDTO> findAll() {
        return droneRepository.findAll().stream().map(d -> modelMapper.map(d, DroneDTO.class)).collect(Collectors.toList());
    }
}
