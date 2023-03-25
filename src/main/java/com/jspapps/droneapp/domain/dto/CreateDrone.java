package com.jspapps.droneapp.domain.dto;

import com.jspapps.droneapp.application.util.constant.DroneType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateDrone {

    private Long id;
    private String serial;

    private DroneType model;
    private Long weight;
    private Long battery;
}
