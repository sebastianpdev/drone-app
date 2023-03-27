package com.jspapps.droneapp.domain.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveringDroneEvent extends AbstractDroneEvent {

    public DeliveringDroneEvent(String droneId) {
        super(droneId);
    }
}
