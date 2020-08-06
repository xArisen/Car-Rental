package com.euvic.carrental.controllers;

import com.euvic.carrental.model.*;
import com.euvic.carrental.responses.*;
import com.euvic.carrental.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/a/car-components")
public class CarResourcesController {

    private final ColourService colourService;
    private final FuelTypeService fuelTypeService;
    private final GearboxTypeService gearboxTypeService;
    private final MarkService markService;
    private final RoleService roleService;
    private final TypeService typeService;

    public CarResourcesController(final ColourService colourService, final FuelTypeService fuelTypeService,
                                  final GearboxTypeService gearboxTypeService, final MarkService markService,
                                  final RoleService roleService, final TypeService typeService) {
        this.colourService = colourService;
        this.fuelTypeService = fuelTypeService;
        this.gearboxTypeService = gearboxTypeService;
        this.markService = markService;
        this.roleService = roleService;
        this.typeService = typeService;
    }

    //GETs

    @RequestMapping(method = RequestMethod.GET, value = "/colours")
    public ResponseEntity getAllColours() {
        return ResponseEntity.ok(colourService.getAllDTOs());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/fuelTypes")
    public ResponseEntity getAllFuelTypes() {
        return ResponseEntity.ok(fuelTypeService.getAllDTOs());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/gearboxTypes")
    public ResponseEntity getAllGearboxTypes() {
        return ResponseEntity.ok(gearboxTypeService.getAllDTOs());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/marks")
    public ResponseEntity getAllMarks() {
        return ResponseEntity.ok(markService.getAllDTOs());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/types")
    public ResponseEntity getAllTypes() {
        return ResponseEntity.ok(typeService.getAllDTOs());
    }

    //POSTs

    @RequestMapping(method = RequestMethod.POST, value = "/addColour")
    public ResponseEntity<?> addNewColour(@RequestBody final ColourDTO colourDTO) {
        colourDTO.setName(colourDTO.getName().toLowerCase());
        final Colour colour = colourService.getEntityByName(colourDTO.getName().toLowerCase());
        Long id = null;
        int responseCode = 400;
        if (colour == null) {
            responseCode = 200;
            id = colourService.addEntityToDB(colourService.mapRestModel(null, colourDTO));
        }
        return ResponseEntity.status(responseCode).body(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addFuelType")
    public ResponseEntity<?> addNewFuelType(@RequestBody final FuelTypeDTO fuelTypeDTO) {
        fuelTypeDTO.setName(fuelTypeDTO.getName().toLowerCase());
        final FuelType fuelType = fuelTypeService.getEntityByName(fuelTypeDTO.getName().toLowerCase());
        Long id = null;
        int responseCode = 400;
        if (fuelType == null) {
            responseCode = 200;
            id = fuelTypeService.addEntityToDB(fuelTypeService.mapRestModel(null, fuelTypeDTO));
        }
        return ResponseEntity.status(responseCode).body(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addGearboxType")
    public ResponseEntity<?> addGearboxType(@RequestBody final GearBoxTypeDTO gearBoxTypeDTO) {
        gearBoxTypeDTO.setName(gearBoxTypeDTO.getName().toLowerCase());
        final GearboxType gearboxType = gearboxTypeService.getEntityByName(gearBoxTypeDTO.getName().toLowerCase());
        Long id = null;
        int responseCode = 400;
        if (gearboxType == null) {
            responseCode = 200;
            id = gearboxTypeService.addEntityToDB(gearboxTypeService.mapRestModel(null, gearBoxTypeDTO));
        }
        return ResponseEntity.status(responseCode).body(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addMark")
    public ResponseEntity<?> addMark(@RequestBody final MarkDTO markDTO) {
        markDTO.setName(markDTO.getName().toLowerCase());
        final Mark mark = markService.getEntityByName(markDTO.getName());
        Long id = null;
        int responseCode = 400;
        if (mark == null) {
            responseCode = 200;
            id = markService.addEntityToDB(markService.mapRestModel(null, markDTO));
        }
        return ResponseEntity.status(responseCode).body(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addType")
    public ResponseEntity<?> addType(@RequestBody final TypeDTO typeDTO) {
        typeDTO.setName(typeDTO.getName().toLowerCase());
        final Type type = typeService.getEntityByName(typeDTO.getName());
        Long id = null;
        int responseCode = 400;
        if (type == null) {
            responseCode = 200;
            id = typeService.addEntityToDB(typeService.mapRestModel(null, typeDTO));
        }
        return ResponseEntity.status(responseCode).body(id);
    }
}
