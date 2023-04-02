package com.jspapps.droneapp.infraestructure.persistence.dao;

import com.jspapps.droneapp.application.util.RandomUUIDGenerator;
import com.jspapps.droneapp.application.util.annotation.PersistenceAdapter;
import com.jspapps.droneapp.domain.dto.RegisterBatteryLevelHistory;
import com.jspapps.droneapp.domain.port.CreateBatteryLevelHistoryPort;
import com.jspapps.droneapp.infraestructure.persistence.BatteryLevelHistoryRepository;
import com.jspapps.droneapp.infraestructure.persistence.model.BatteryLevelHistory;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
@PersistenceAdapter
public class CreateBatteryLevelHistoryDAO implements CreateBatteryLevelHistoryPort {

    private final ModelMapper modelMapper;
    private final BatteryLevelHistoryRepository batteryLevelHistoryRepository;

    @Override
    public RegisterBatteryLevelHistory createBatteryLevelLog(RegisterBatteryLevelHistory batteryLevelHistory) {
        var recordBatteryLevel = modelMapper.map(batteryLevelHistory, BatteryLevelHistory.class);
        recordBatteryLevel.setId(RandomUUIDGenerator.generateRandomUUIDString());
        recordBatteryLevel = batteryLevelHistoryRepository.save(recordBatteryLevel);
        return modelMapper.map(recordBatteryLevel, RegisterBatteryLevelHistory.class);
    }
}
