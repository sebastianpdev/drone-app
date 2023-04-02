package com.jspapps.droneapp.domain.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayloadDroneDTO {

    private String droneId;
    private String droneSerial;
    private String medicationId;
    private String medicationName;
    private Long medicationLoad;
    private Long quantity;
}
