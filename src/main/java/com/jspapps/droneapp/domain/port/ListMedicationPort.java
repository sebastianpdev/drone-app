package com.jspapps.droneapp.domain.port;

import java.util.Set;

public interface ListMedicationPort {

    Long findMedicationWeightById(String medicationId);

    Long sumMedicationsWeightByIds(Set<String> medications);
}
