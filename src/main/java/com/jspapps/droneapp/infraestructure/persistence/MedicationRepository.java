package com.jspapps.droneapp.infraestructure.persistence;

import com.jspapps.droneapp.infraestructure.persistence.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, String> {

    List<Medication> findAllByIdIn(Collection<String> id);

    @Query(value = "select sum(m.weight) from Medication m where m.id in :medications")
    Long sumMedicationsWeightByIds(@Param("medications") Set<String> medications);

    @Query(value = "select m.weight from Medication m where m.id = :id")
    Long findMedicationWeightById(@Param("id") String medicationId);
}
