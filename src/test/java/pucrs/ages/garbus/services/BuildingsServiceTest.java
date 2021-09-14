package pucrs.ages.garbus.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pucrs.ages.garbus.dtos.BuildingsDTO;
import pucrs.ages.garbus.dtos.ZonesDTO;
import pucrs.ages.garbus.entities.Buildings;
import pucrs.ages.garbus.entities.Zones;
import pucrs.ages.garbus.mappers.BuildingsMapper;
import pucrs.ages.garbus.mappers.ZonesMapper;
import pucrs.ages.garbus.repositories.BuildingsRepository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class BuildingsServiceTest {

    @InjectMocks
    private BuildingsService buildingsService;

    @Mock
    private TrashesService trashesService;

    @Mock
    private BuildingsRepository buildingsRepository;

    @Mock
    private ZonesService zonesService;

    @Mock
    private ZonesMapper zonesMapper;

    private ModelMapper modelMapper;
    private BuildingsMapper buildingsMapper;

    @BeforeEach
    void setup() {
        modelMapper = new ModelMapper();
        buildingsMapper = new BuildingsMapper(modelMapper);
        buildingsService = new BuildingsService(buildingsMapper, buildingsRepository, trashesService, zonesService, zonesMapper);
    }

    @Test
    void findAll() {
        //Given
        List<Buildings> buildings = generateBuildingEntitiesList();
        List<BuildingsDTO> buildingsDTO = generateBuildingDtoList();

        //When
        given(buildingsRepository.findAll()).willReturn(buildings);
        given(trashesService.getTrashesCountByBuilding(any())).willReturn(Long.parseLong("1"));

        List<BuildingsDTO> response = buildingsService.findAll();

        //Then
        then(response.size()).isEqualTo(1);
        then(response.get(0).getName()).isEqualTo(buildingsDTO.get(0).getName());
        then(response.get(0).getTrashesCount()).isEqualTo(buildingsDTO.get(0).getTrashesCount());
        then(response.get(0).getZones()).isEqualTo(buildingsDTO.get(0).getZones());
    }

    @Test
    void findById() {
        //Given
        List<Buildings> buildings = generateBuildingEntitiesList();
        List<BuildingsDTO> buildingsDTO = generateBuildingDtoList();

        //When
        given(buildingsRepository.findById(any())).willReturn(java.util.Optional.ofNullable(buildings.get(0)));
        given(trashesService.getTrashesCountByBuilding(any())).willReturn(Long.parseLong("1"));

        BuildingsDTO response = buildingsService.findById(Long.parseLong("1"));

        //Then
        then(response.getName()).isEqualTo(buildingsDTO.get(0).getName());
        then(response.getTrashesCount()).isEqualTo(buildingsDTO.get(0).getTrashesCount());
        then(response.getZones()).isEqualTo(buildingsDTO.get(0).getZones());
    }

    @Test
    void save() throws ParseException {
        //Given
        List<Buildings> buildings = generateBuildingEntitiesList();
        List<BuildingsDTO> buildingsDTO = generateBuildingDtoList();
        ZonesDTO zonesDTO = ZonesDTO.builder().id(1).name("Zone test").build();

        //When
        given(zonesService.findById(any())).willReturn(zonesDTO);
        given(buildingsRepository.saveAndFlush(any())).willReturn(buildings.get(0));

        BuildingsDTO response = buildingsService.save(buildingsDTO.get(0));

        //Then
        then(response.getName()).isEqualTo(buildingsDTO.get(0).getName());
        then(response.getZones()).isEqualTo(buildingsDTO.get(0).getZones());
    }

    private List<BuildingsDTO> generateBuildingDtoList() {
        List<BuildingsDTO> buildingsDTO = new ArrayList<>();
        buildingsDTO.add(BuildingsDTO.builder()
                .id(1)
                .name("Building test")
                .trashesCount(Long.parseLong("1"))
                .zones(Zones.builder()
                        .id(1)
                        .name("Zone test")
                        .build())
                .build());

        return buildingsDTO;
    }

    private List<Buildings> generateBuildingEntitiesList() {
        List<Buildings> buildings = new ArrayList<>();
        buildings.add(Buildings.builder()
                .id(1)
                .name("Building test")
                .zones(Zones.builder()
                        .id(1)
                        .name("Zone test")
                        .build())
                .build());

        return buildings;
    }
}