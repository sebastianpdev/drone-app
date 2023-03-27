package com.jspapps.droneapp.domain.event;

import com.jspapps.droneapp.application.util.constant.DroneState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDroneLoadEvent extends AbstractDroneEvent {

    private DroneState droneStatus;

    public ReviewDroneLoadEvent(String droneId) {
        super(droneId);
    }

    public ReviewDroneLoadEvent(String droneId, DroneState droneState) {
        super(droneId);
        this.droneStatus = droneState;
    }

}
