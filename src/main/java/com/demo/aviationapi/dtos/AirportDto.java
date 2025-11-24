package com.demo.aviationapi.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO representing an airport with metadata fetched from Airport API.
 */
@Data
@NoArgsConstructor
@Getter
@Setter
public class AirportDto {

    private String site_number;
    private String type;
    private String facility_name;
    private String faa_ident;
    private String icao_ident;
    private String district_office;
    private String state;
    private String state_full;
    private String county;
    private String city;
    private String ownership;
    private String use;
    private String manager;
    private String manager_phone;
    private String latitude;
    private String latitude_sec;
    private String longitude;
    private String longitude_sec;
    private String elevation;
    private String magnetic_variation;
    private String tpa;
    private String vfr_sectional;
    private String boundary_artcc;
    private String boundary_artcc_name;
    private String responsible_artcc;
    private String responsible_artcc_name;
    private String fss_phone_number;
    private String fss_phone_number_toll_free;
    private String notam_facility_ident;
    private String status;
    private String certification_type_date;
    private String customs_airport_of_entry;
    private String military_joint_use;
    private String military_landing;
    private String lighting_schedule;
    private String beacon_schedule;
    private String control_tower;
    private String unicom;
    private String ctaf;
    private String effective_date;
}
