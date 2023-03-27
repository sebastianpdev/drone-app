package com.jspapps.droneapp.domain.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterLoadDrone {

    private String id;
    private String droneId;
    private String medicationId;
    private Long medicationLoad;
    private Long quantity;
}
