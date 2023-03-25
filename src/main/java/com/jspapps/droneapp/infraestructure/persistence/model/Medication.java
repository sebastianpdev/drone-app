package com.jspapps.droneapp.infraestructure.persistence.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "medication")
public class Medication implements Serializable {

    private static final long serialVersionUUID = 1L;

    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private Long weight;

    @NotNull
    private String code;

    private String image;
}
