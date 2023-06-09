package com.jspapps.droneapp.domain.dto;

import com.jspapps.droneapp.application.util.constant.DroneType;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDrone {

    private String id;
    @NotNull
    private String serial;
    @NotNull
    private DroneType model;
    @NotNull
    private Long weight;
    private Long battery;
}
