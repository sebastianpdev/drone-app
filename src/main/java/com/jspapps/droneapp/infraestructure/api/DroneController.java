package com.jspapps.droneapp.infraestructure.api;

import com.jspapps.droneapp.domain.dto.CreateDrone;
import com.jspapps.droneapp.domain.dto.DroneDTO;
import com.jspapps.droneapp.domain.dto.PayloadDroneDTO;
import com.jspapps.droneapp.domain.dto.PayloadDroneRequest;
import com.jspapps.droneapp.domain.usecase.CreateDroneUseCase;
import com.jspapps.droneapp.domain.usecase.CreatePayloadDroneUseCase;
import com.jspapps.droneapp.domain.usecase.ListDroneUseCase;
import com.jspapps.droneapp.domain.usecase.ListPayloadDroneUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("drone")
public class DroneController {

    private final CreateDroneUseCase createDroneUseCase;
    private final CreatePayloadDroneUseCase createPayloadDroneUseCase;
    private final ListPayloadDroneUseCase listPayloadDroneUseCase;
    private final ListDroneUseCase listDroneUseCase;

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

    @GetMapping(value = "/v1/current-payload/{drone}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PayloadDroneDTO>> currentPayloadByDrone(@PathVariable("drone") String drone) {
        var payloadDrone = listPayloadDroneUseCase.findMedicationLoadedByDrone(drone);
        return ResponseEntity.ok(payloadDrone);
    }

    @GetMapping(value = "/v1/available", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DroneDTO>> findAllAvailable() {
        var droneList = listDroneUseCase.findAllAvailable();
        return ResponseEntity.ok(droneList);
    }

    @GetMapping(value = "/v1/current-battery-level/{drone}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Long>> getCurrentBatteryLevelByDrone(@PathVariable("drone") String droneId) {
        var drone = listDroneUseCase.findDroneById(droneId);
        Map<String, Long> droneResponse = new HashMap<>();
        droneResponse.put("battery_level", drone.getBattery());
        return ResponseEntity.ok(droneResponse);
    }

}
