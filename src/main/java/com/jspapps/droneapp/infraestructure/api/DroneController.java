package com.jspapps.droneapp.infraestructure.api;

import com.jspapps.droneapp.domain.dto.CreateDrone;
import com.jspapps.droneapp.domain.dto.PayloadDroneDTO;
import com.jspapps.droneapp.domain.dto.PayloadDroneRequest;
import com.jspapps.droneapp.domain.usecase.CreateDroneUseCase;
import com.jspapps.droneapp.domain.usecase.CreatePayloadDroneUseCase;
import com.jspapps.droneapp.domain.usecase.ListPayloadDroneUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("drone")
public class DroneController {

    private final CreateDroneUseCase createDroneUseCase;
    private final CreatePayloadDroneUseCase createPayloadDroneUseCase;
    private final ListPayloadDroneUseCase listPayloadDroneUseCase;

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

    @GetMapping(value = "/v1/current-payload/{drone}")
    public ResponseEntity<List<PayloadDroneDTO>> currentPayloadByDrone(@PathVariable("drone") String drone) {
        var payloadDrone = listPayloadDroneUseCase.findMedicationLoadedByDrone(drone);
        return ResponseEntity.ok(payloadDrone);
    }

}
