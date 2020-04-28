package com.analuciabolico.assembly.v1.associated.service.implementation;

import com.analuciabolico.assembly.v1.associated.model.Associated;
import com.analuciabolico.assembly.v1.associated.repository.AssociatedRepository;
import com.analuciabolico.assembly.v1.core.BaseUnityTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityNotFoundException;

import java.util.List;

import static com.analuciabolico.assembly.v1.core.Constants.*;
import static com.analuciabolico.assembly.v1.core.validation.GenericMessagesValidationEnum.ENTITY_NOT_FOUND;
import static com.analuciabolico.assembly.v1.core.validation.MessageValidationProperties.getMessage;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

class AssociatedServiceTest extends BaseUnityTest {

    @InjectMocks
    AssociatedService associatedService;

    @Mock
    AssociatedRepository associatedRepository;

    @Test
    @DisplayName("Test save")
    void save() {
        doReturn(oneAssociated()).when(associatedRepository).save(any());
        assertEquals(oneResourceCreated().getId(), associatedService.save(oneAssociatedDto()).getId());
    }

    @Test
    @DisplayName("Test save Exception")
    void saveException() {
        doThrow(oneDataIntegrityViolationException()).when(associatedRepository).save(any());

        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> associatedService.save(oneAssociatedDto()));
        assertEquals(PRIMARY_KEY_VIOLATION, exception.getMessage());
    }

    @Test
    @DisplayName("Test findById")
    void findById() {
        doReturn(oneAssociatedOptional()).when(associatedRepository).findById(any());

        Associated associated = associatedService.findById(ONE_LONG);
        assertAll("associated",
                () -> assertEquals(ONE_LONG, associated.getId()),
                () -> assertEquals(NAME, associated.getName()),
                () -> assertEquals(VALID_CPF, associated.getCpf()),
                () -> assertEquals(CREATED_AT, associated.getCreatedAt())
        );
    }

    @Test
    @DisplayName("Test findById exception")
    void findByIdException() {
        doReturn(oneAssociatedOptionalEmpty()).when(associatedRepository).findById(any());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> associatedService.findById(ONE_LONG));
        assertEquals(getMessage(ENTITY_NOT_FOUND), exception.getMessage());
    }

    @Test
    @DisplayName("Test findAll")
    void findAll() {
        doReturn(oneAssociatedList()).when(associatedRepository).findAll(any(Sort.class));

        List<Associated> associateds = associatedService.findAll(oneSortAsc());
        assertAll("associateds",
                () -> assertFalse(associateds.isEmpty()),
                () -> assertEquals(1, associateds.size()),
                () -> assertEquals(ONE_LONG, associateds.get(0).getId()),
                () -> assertEquals(NAME, associateds.get(0).getName()),
                () -> assertEquals(VALID_CPF, associateds.get(0).getCpf()),
                () -> assertEquals(CREATED_AT, associateds.get(0).getCreatedAt())
        );
    }

    @Test
    @DisplayName("Test findAll No Content")
    void findAllNoContent() {
        doReturn(oneAssociatedListEmpty()).when(associatedRepository).findAll(any(Sort.class));

        List<Associated> associateds = associatedService.findAll(oneSortAsc());
        assertAll("associateds",
                () -> assertTrue(associateds.isEmpty()),
                () -> assertEquals(0, associateds.size())
        );
    }

}