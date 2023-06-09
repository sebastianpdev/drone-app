package com.jspapps.droneapp.infraestructure.persistence.model;

import com.jspapps.droneapp.application.util.constant.DroneState;
import com.jspapps.droneapp.application.util.constant.DroneType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "drone")
public class Drone implements Serializable {

    private static final long serialVersionUUID = 1L;

    @Id
    private String id;

    @NotNull
    @Size(max = 100)
    @Column(name = "serial")
    private String serial;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "model")
    private DroneType model;

    @NotNull
    @Column(name = "weight")
    private Long weight;

    @NotNull
    @Column(name = "battery")
    private Long battery;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private DroneState state;

}
