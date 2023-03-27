package com.jspapps.droneapp.domain.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReturningDroneEvent extends AbstractDroneEvent {

    public ReturningDroneEvent(String droneId) {
        super(droneId);
    }
}
