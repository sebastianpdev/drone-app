package com.jspapps.droneapp.domain.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdleDroneEvent extends AbstractDroneEvent {

    public IdleDroneEvent(String droneId) {
        super(droneId);
    }
}
