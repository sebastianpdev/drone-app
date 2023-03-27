package com.jspapps.droneapp.domain.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoadedDroneEvent extends AbstractDroneEvent {

    public LoadedDroneEvent(String droneId) {
        super(droneId);
    }
}
