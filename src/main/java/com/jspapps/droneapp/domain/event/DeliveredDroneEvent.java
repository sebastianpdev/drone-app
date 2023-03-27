package com.jspapps.droneapp.domain.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveredDroneEvent extends AbstractDroneEvent {

    public DeliveredDroneEvent(String droneId) {
        super(droneId);
    }
}
