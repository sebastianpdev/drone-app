package com.jspapps.droneapp.infraestructure.persistence.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "drone_load")
public class DroneLoad {

    private static final long serialVersionUUID = 1L;

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "drone_id")
    private Drone drone;

    @ManyToOne
    @JoinColumn(name = "medication_id")
    private Medication medication;

    @Column(name = "medication_load")
    private Long medicationLoad;

    @Column(name = "quantity")
    private Long quantity;
}
