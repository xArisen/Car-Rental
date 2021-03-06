package com.euvic.carrental.services;

import com.euvic.carrental.model.Type;
import com.euvic.carrental.repositories.TypeRepository;
import com.euvic.carrental.responses.TypeDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("h2")
public class TypeServiceTest {

    @Autowired
    private TypeService typeService;

    @Autowired
    private TypeRepository typeRepository;

    @AfterEach
    void tearDown() {
        typeRepository.deleteAll();
    }


    @Test
    void whenTypeDTOGiven_thenReturnTypeEntity() {
        final Type type = new Type(null, "Sedan");
        final TypeDTO typeDTO = new TypeDTO("Sedan");
        assertAll(() -> {
            assertEquals(typeService.mapRestModel(null, typeDTO).getName(), type.getName());
            assertEquals(typeService.mapRestModel(null, typeDTO).getId(), type.getId());
        });
    }


    @Test
    void shouldReturnDBColourEntity() {
        final Type type = new Type(null, "Sedan");
        assertEquals(0, typeRepository.count());
        typeRepository.save(type);
        assertEquals(1, typeRepository.count());
        final Type serviceType = typeService.getEntityByName("Sedan");

        assertAll(() -> {
            assertEquals(type.getName(), serviceType.getName());
            assertNotEquals(null, serviceType.getId());
        });
    }

    @Test
    void shouldReturnDBTypeDTO() {
        final Type type = new Type(null, "Sedan");
        assertEquals(0, typeRepository.count());
        typeRepository.save(type);
        assertEquals(1, typeRepository.count());

        final TypeDTO serviceTypeDTO = typeService.getDTOByName("Sedan");

        assertEquals(type.getName(), serviceTypeDTO.getName());
    }

    @Test
    void shouldReturnAllDBTypesDTO() {
        final Type type1 = new Type(null, "Sedan");
        final Type type2 = new Type(null, "Coupe");
        final Type type3 = new Type(null, "Van");
        assertEquals(0, typeRepository.count());
        typeRepository.save(type1);
        typeRepository.save(type2);
        typeRepository.save(type3);
        assertEquals(3, typeRepository.count());

        final List<TypeDTO> typeDTOList = typeService.getAllDTOs();

        assertEquals(typeRepository.count(), typeDTOList.size());
    }

}
