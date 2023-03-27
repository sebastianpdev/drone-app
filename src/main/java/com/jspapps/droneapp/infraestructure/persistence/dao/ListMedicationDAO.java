package com.jspapps.droneapp.infraestructure.persistence.dao;

import com.jspapps.droneapp.application.util.annotation.PersistenceAdapter;
import com.jspapps.droneapp.domain.port.ListMedicationPort;
import com.jspapps.droneapp.infraestructure.persistence.MedicationRepository;
import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@PersistenceAdapter
public class ListMedicationDAO implements ListMedicationPort {

    private final MedicationRepository medicationRepository;

    @Override
    public Long findMedicationWeightById(String medicationId) {
        return medicationRepository.findMedicationWeightById(medicationId);
    }

    @Override
    public Long sumMedicationsWeightByIds(Set<String> medications) {
        return medicationRepository.sumMedicationsWeightByIds(medications);
    }
}
