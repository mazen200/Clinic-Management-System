package com.netpoint.clinic.service;

import com.netpoint.clinic.dtos.CreateSpecializationDTO;
import com.netpoint.clinic.dtos.SpecializationResponseDTO;
import com.netpoint.clinic.mappers.SpecializationMapper;
import com.netpoint.clinic.model.Specializtions;
import com.netpoint.clinic.repository.SpecializationRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("SpecializationService Unit Tests")
class SpecializationServiceTest {

    @Mock private SpecializationRepo specRepo;
    @Mock private SpecializationMapper specializationMapper;
    @InjectMocks private SpecializationService specializationService;

    private Specializtions spec;
    private SpecializationResponseDTO responseDTO;
    private CreateSpecializationDTO createDTO;

    @BeforeEach
    void setUp() {
        spec = new Specializtions();
        spec.setName("Cardiology");
        spec.setDescription("Heart and cardiovascular system");

        responseDTO = new SpecializationResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setName("Cardiology");
        responseDTO.setDescription("Heart and cardiovascular system");

        createDTO = new CreateSpecializationDTO();
        createDTO.setName("Cardiology");
        createDTO.setDescription("Heart and cardiovascular system");
    }

    @Nested
    @DisplayName("CreateSpecialization")
    class Create {

        @Test
        @DisplayName("saves specialization and returns DTO")
        void savesAndReturnsDto() {
            when(specRepo.save(any(Specializtions.class))).thenReturn(spec);
            when(specializationMapper.toDto(spec)).thenReturn(responseDTO);

            SpecializationResponseDTO result = specializationService.CreateSpecialization(createDTO);

            assertThat(result).isNotNull();
            assertThat(result.getName()).isEqualTo("Cardiology");
            assertThat(result.getId()).isEqualTo(1L);
            verify(specRepo).save(any(Specializtions.class));
        }

        @Test
        @DisplayName("maps name and description from request to entity before saving")
        void mapsFieldsCorrectly() {
            when(specRepo.save(argThat(s ->
                    "Cardiology".equals(s.getName()) &&
                    "Heart and cardiovascular system".equals(s.getDescription())
            ))).thenReturn(spec);
            when(specializationMapper.toDto(spec)).thenReturn(responseDTO);

            specializationService.CreateSpecialization(createDTO);

            verify(specRepo).save(argThat(s ->
                    "Cardiology".equals(s.getName())
            ));
        }
    }

    @Nested
    @DisplayName("findallSpecs")
    class FindAll {

        @Test
        @DisplayName("returns all specializations as DTOs")
        void returnsDtoList() {
            when(specRepo.findAll()).thenReturn(List.of(spec));
            when(specializationMapper.toDto(spec)).thenReturn(responseDTO);

            List<SpecializationResponseDTO> result = specializationService.findallSpecs();

            assertThat(result).hasSize(1);
            assertThat(result.get(0).getName()).isEqualTo("Cardiology");
        }

        @Test
        @DisplayName("returns empty list when no specializations exist")
        void returnsEmptyList() {
            when(specRepo.findAll()).thenReturn(List.of());

            List<SpecializationResponseDTO> result = specializationService.findallSpecs();

            assertThat(result).isEmpty();
            verifyNoInteractions(specializationMapper);
        }
    }
}
