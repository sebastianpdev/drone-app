package com.jspapps.droneapp.infraestructure.api;

import com.jspapps.droneapp.domain.dto.CreateDrone;
import com.jspapps.droneapp.domain.dto.PayloadDroneRequest;
import com.jspapps.droneapp.domain.usecase.CreateDroneUseCase;
import com.jspapps.droneapp.domain.usecase.CreatePayloadDroneUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("drone")
public class DroneController {

    private final CreateDroneUseCase createDroneUseCase;
    private final CreatePayloadDroneUseCase createPayloadDroneUseCase;

    @PostMapping(value = "/v1/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createDrone(@RequestBody @Valid CreateDrone drone) {
        createDroneUseCase.createDrone(drone);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/v1/load-medication", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> loadDrone(@RequestBody PayloadDroneRequest payloadDroneRequest) {
        createPayloadDroneUseCase.registerLoad(payloadDroneRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
