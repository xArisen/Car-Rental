package com.euvic.carrental.services;

import com.euvic.carrental.model.*;
import com.euvic.carrental.repositories.*;
import com.euvic.carrental.responses.CarDTO;
import com.euvic.carrental.responses.FaultDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("h2")
public class FaultServiceTest {

    @Autowired
    private FaultRepository faultRepository;

    @Autowired
    private FaultService faultService;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private GearboxTypeService gearboxTypeService;

    @Autowired
    private GearboxTypeRepository gearboxTypeRepository;

    @Autowired
    private FuelTypeService fuelTypeService;

    @Autowired
    private FuelTypeRepository fuelTypeRepository;

    @Autowired
    private MarkService markService;

    @Autowired
    private MarkRepository markRepository;

    @Autowired
    private ModelService modelService;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private ColourService colourService;

    @Autowired
    private ColourRepository colourRepository;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TypeRepository typeRepository;

    // TODO optymalizacja @BeforeEach
    @BeforeEach
    void setUp() {
        final GearboxType gearboxType1 = new GearboxType(null, "Manual");
        final GearboxType gearboxType2 = new GearboxType(null, "Automatic");
        final GearboxType gearboxType3 = new GearboxType(null, "Other");

        gearboxTypeRepository.save(gearboxType1);
        gearboxTypeRepository.save(gearboxType2);
        gearboxTypeRepository.save(gearboxType3);


        final FuelType fuelType1 = new FuelType(null, "Gasoline");
        final FuelType fuelType2 = new FuelType(null, "Petrol");
        final FuelType fuelType3 = new FuelType(null, "Diesel");

        fuelTypeRepository.save(fuelType1);
        fuelTypeRepository.save(fuelType2);
        fuelTypeRepository.save(fuelType3);

        final Mark mark1 = new Mark(null, "Audi");
        final Mark mark2 = new Mark(null, "Opel");
        final Mark mark3 = new Mark(null, "BMW");

        markRepository.save(mark1);
        markRepository.save(mark2);
        markRepository.save(mark3);

        final Model model1 = new Model(null, "C350", markService.getEntityByName("Audi"));
        final Model model2 = new Model(null, "Astra", markService.getEntityByName("Opel"));
        final Model model3 = new Model(null, "M5", markService.getEntityByName("BMW"));

        modelRepository.save(model1);
        modelRepository.save(model2);
        modelRepository.save(model3);

        final Parking parking1 = new Parking(null, "Katowice", "40-001", "Bydgoska 23", "E-6", "Parking przy sklepiku Avea", true);
        final Parking parking2 = new Parking(null, "Radom", "40-222", "Jaka 32", "A-8", "Parking przy sklepie Tesco", true);
        final Parking parking3 = new Parking(null, "Kielce", "40-623", "Weteranow 54", "B-4", "Parking przy dworcu", true);

        Long parkingId1 = parkingService.addEntityToDB(parking1);

        final Colour colour1 = new Colour(null, "Red");
        final Colour colour2 = new Colour(null, "Blue");
        final Colour colour3 = new Colour(null, "Green");

        colourRepository.save(colour1);
        colourRepository.save(colour2);
        colourRepository.save(colour3);

        final Type type1 = new Type(null, "Sedan");
        final Type type2 = new Type(null, "Coupe");
        final Type type3 = new Type(null, "Van");

        typeRepository.save(type1);
        typeRepository.save(type2);
        typeRepository.save(type3);


        final Car car = new Car(null, "photoNr2", "SBE33212", 120, 1, 4, 3,
                gearboxTypeService.getEntityByName("Manual"), fuelTypeService.getEntityByName("Diesel"),
                new Date(2000, 3, 25), 2000, true, true, 120000, modelService.getEntityByName("Astra"),
                parkingService.getEntityById(parkingId1), colourService.getEntityByName("Blue"), typeService.getEntityByName("Coupe"));

        carRepository.save(car);
    }

    @AfterEach
    void tearDown() {
        faultRepository.deleteAll();
        carRepository.deleteAll();
        gearboxTypeRepository.deleteAll();
        fuelTypeRepository.deleteAll();
        modelRepository.deleteAll();
        markRepository.deleteAll();
        parkingRepository.deleteAll();
        colourRepository.deleteAll();
        typeRepository.deleteAll();

    }

    @Test
    void whenFaultDTOGiven_thenReturnFaultEntity() {
        final Car car = carService.getEntityByLicensePlate("SBE33212");
        final CarDTO carDTO = carService.getDTOByLicensePlate("SBE33212");

        final Fault fault = new Fault(null, car, "nothing", true);

        final FaultDTO faultDTO = new FaultDTO(carDTO, "nothing", true);

        final Fault restModelToEntityModel = faultService.mapRestModel(faultDTO);
        assertAll(() -> {
            assertEquals(restModelToEntityModel.getCar(), fault.getCar());
            assertEquals(restModelToEntityModel.getId(), fault.getId());
            assertEquals(restModelToEntityModel.getDescribe(), fault.getDescribe());
            assertEquals(restModelToEntityModel.getIsActive(), fault.getIsActive());
        });
    }

    @Test
    void shouldReturnDBFaultEntity() {
        final Car car = carService.getEntityByLicensePlate("SBE33212");

        final Fault fault = new Fault(null, car, "nothing", true);

        assertEquals(0, faultRepository.count());
        Long faultId = faultService.addEntityToDB(fault);
        assertEquals(1, faultRepository.count());

        final Fault serviceFault1 = faultService.getAllEntitiesByCar(car).get(0);
        final Fault serviceFault2 = faultService.getEntityById(faultId);

        assertAll(() -> {
            assertNotEquals(null, serviceFault1.getId());
            assertEquals(fault.getCar(), serviceFault1.getCar());
            assertEquals(fault.getDescribe(), serviceFault1.getDescribe());
            assertEquals(fault.getIsActive(), serviceFault1.getIsActive());


            assertNotEquals(null, serviceFault2.getId());
            assertEquals(fault.getCar(), serviceFault2.getCar());
            assertEquals(fault.getDescribe(), serviceFault2.getDescribe());
            assertEquals(fault.getIsActive(), serviceFault2.getIsActive());
        });
    }

    @Test
    void shouldReturnDBFaultDTO() {
        final Car car = carService.getEntityByLicensePlate("SBE33212");

        final Fault fault = new Fault(null, car, "nothing", true);
        assertEquals(0, faultRepository.count());
        Long faultId = faultService.addEntityToDB(fault);
        assertEquals(1, faultRepository.count());

        final FaultDTO faultDTO1 = faultService.getAllDTOsByCar(car).get(0);
        final FaultDTO faultDTO2 = faultService.getDTOById(faultId);

        assertAll(() -> {
            assertEquals(fault.getIsActive(), faultDTO1.getIsActive());
            assertEquals(fault.getDescribe(), faultDTO1.getDescribe());
            assertEquals(fault.getCar().getLicensePlate(), faultDTO1.getCarDTO().getLicensePlate());


            assertEquals(fault.getIsActive(), faultDTO2.getIsActive());
            assertEquals(fault.getDescribe(), faultDTO2.getDescribe());
            assertEquals(fault.getCar().getLicensePlate(), faultDTO2.getCarDTO().getLicensePlate());
        });
    }

    @Test
    void shouldReturnAllDBFaultsDTO() {
        final Car car = carService.getEntityByLicensePlate("SBE33212");

        final Fault fault1 = new Fault(null, car, "sd", true);
        final Fault fault2 = new Fault(null, car, "dd", true);
        final Fault fault3 = new Fault(null, car, "we", true);

        assertEquals(0, faultRepository.count());
        faultRepository.save(fault1);
        faultRepository.save(fault2);
        faultRepository.save(fault3);
        assertEquals(3, faultRepository.count());

        final List<FaultDTO> faultDTOList = faultService.getAllDTOsByCar(car);

        assertEquals(faultRepository.count(), faultDTOList.size());
    }

    @Test
    void whenFaultEntityGiven_shouldAddFaultEntityToDB() {
        final Car car = carService.getEntityByLicensePlate("SBE33212");

        final Fault fault = new Fault(null, car, "nothing", true);

        assertEquals(0, faultRepository.count());
        faultService.addEntityToDB(fault);
        assertEquals(1, faultRepository.count());
    }
}
