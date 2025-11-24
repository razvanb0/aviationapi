package com.demo.aviationapi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Airport {
    private String facilityName;
    private String state;
    private String stateFull;
    private String county;
    private String city;
    private String manager;
    private String managerPhone;
    private String status;
    private String boundaryArtcc;
    private String boundaryArtccName;
    private String responsibleArtcc;
    private String responsibleArtccName;
}
