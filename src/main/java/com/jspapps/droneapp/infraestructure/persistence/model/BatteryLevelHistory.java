package com.jspapps.droneapp.infraestructure.persistence.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "battery_level_history")
public class BatteryLevelHistory implements Serializable {

    private static final long serialVersionUUID = 1L;

    @Id
    private String id;

    @NotNull
    @Column(name = "serial_drone")
    private String serialDrone;

    @NotNull
    @Column(name = "battery_level")
    private Long batteryLevel;

    @NotNull
    @Column(name = "created_date")
    private Instant createdDate;
}
