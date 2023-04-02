package com.jspapps.droneapp.domain.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterBatteryLevelHistory {

    private String id;
    private String serialDrone;
    private Long batteryLevel;
    private Instant createdDate;
}
