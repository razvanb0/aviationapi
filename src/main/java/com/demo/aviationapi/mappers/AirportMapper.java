package com.demo.aviationapi.mappers;

import org.springframework.stereotype.Component;

import com.demo.aviationapi.domain.Airport;
import com.demo.aviationapi.dtos.AirportDto;

import lombok.NoArgsConstructor;

/**
 *  Mapper class for converting from {@link AirportDto} to {@link Airport}
 */
@Component
@NoArgsConstructor
public class AirportMapper {

    public Airport map(final AirportDto source){
        if(source == null){
            return null;
        }

        Airport target = new Airport();
        target.setFacilityName(source.getFacility_name());
        target.setState(source.getState());
        target.setStateFull(source.getState_full());
        target.setCounty(source.getCounty());
        target.setCity(source.getCity());
        target.setManager(source.getManager());
        target.setManagerPhone(source.getManager_phone());
        target.setStatus(source.getStatus());
        target.setBoundaryArtcc(source.getBoundary_artcc());
        target.setBoundaryArtccName(source.getBoundary_artcc_name());
        target.setResponsibleArtcc(source.getResponsible_artcc());
        target.setResponsibleArtccName(source.getResponsible_artcc_name());

        return target;
    };


}
