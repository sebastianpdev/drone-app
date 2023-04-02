package com.jspapps.droneapp.domain.port;

import com.jspapps.droneapp.domain.dto.RegisterBatteryLevelHistory;

public interface CreateBatteryLevelHistoryPort {

    RegisterBatteryLevelHistory createBatteryLevelLog(RegisterBatteryLevelHistory batteryLevelHistory);
}
