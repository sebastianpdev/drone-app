package com.jspapps.droneapp.infraestructure.api;

import com.jspapps.droneapp.domain.usecase.CreateDroneUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("drone")
public class DroneController {

    private final CreateDroneUseCase createDroneUseCase;


}
