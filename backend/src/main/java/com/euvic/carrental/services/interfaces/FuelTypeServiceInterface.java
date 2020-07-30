package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.FuelType;
import com.euvic.carrental.responses.FuelTypeDTO;

import java.util.List;

public interface FuelTypeServiceInterface {
    FuelType mapRestModel(Long id, FuelTypeDTO model);

    FuelType getEntityByName(String name);

    FuelTypeDTO getDTOByName(String name);

    List<FuelTypeDTO> getAllDTOs();
}
