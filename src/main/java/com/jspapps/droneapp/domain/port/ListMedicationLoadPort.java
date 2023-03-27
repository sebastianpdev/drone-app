package com.jspapps.droneapp.domain.port;

public interface ListMedicationLoadPort {

    Long currentMedicationLoadByDrone(String droneId);
}
