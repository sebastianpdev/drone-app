package com.jspapps.droneapp.domain.usecase;

import com.jspapps.droneapp.application.util.annotation.UseCase;
import com.jspapps.droneapp.domain.port.CreateDronePort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCase
public class CreateDroneUseCase {

    private final CreateDronePort createDronePort;


}
