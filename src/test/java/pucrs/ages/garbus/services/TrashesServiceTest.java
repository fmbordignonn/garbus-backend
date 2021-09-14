package pucrs.ages.garbus.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pucrs.ages.garbus.dtos.*;
import pucrs.ages.garbus.entities.*;
import pucrs.ages.garbus.mappers.SimplifiedTrashesWithThresholdsMapper;
import pucrs.ages.garbus.mappers.TrashDetailsMapper;
import pucrs.ages.garbus.mappers.TrashesMapper;
import pucrs.ages.garbus.mappers.TrashesThresholdMapper;
import pucrs.ages.garbus.repositories.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class TrashesServiceTest {

    @Mock
    private TrashesRepository trashesRepository;

    @Mock
    private TrashesStatusRepository trashesStatusRepository;

    @Mock
    private TrashesThresholdsRepository trashesThresholdsRepository;

    @Mock
    private ZonesRepository zonesRepository;

    @Mock
    private BuildingsRepository buildingsRepository;

    @Mock
    private TrashesEventsRepository trashesEventsRepository;

    @Mock
    private EventsRepository eventsRepository;

    @Mock
    private NotificationTokensRepository notificationTokensRepository;

    @Mock
    private UsersRepository usersRepository;

    private TrashesService trashesService;

    @Mock
    private FirebaseMessagingService firebaseMessagingService;


    private ModelMapper modelMapper;
    private TrashesMapper trashMapper;
    private TrashDetailsMapper trashDetailsMapper;
    private TrashesThresholdMapper trashesThresholdMapper;
    private SimplifiedTrashesWithThresholdsMapper simplifiedTrashesWithThresholdsMapper;

    @BeforeEach
    void setup() {
        modelMapper = new ModelMapper();
        trashMapper = new TrashesMapper(modelMapper);
        trashesThresholdMapper = new TrashesThresholdMapper(modelMapper);
        trashDetailsMapper = new TrashDetailsMapper(trashesThresholdMapper);
        simplifiedTrashesWithThresholdsMapper = new SimplifiedTrashesWithThresholdsMapper(trashesThresholdMapper, trashMapper);
        trashesService = new TrashesService(
                simplifiedTrashesWithThresholdsMapper, notificationTokensRepository, trashesThresholdsRepository,
                firebaseMessagingService, trashesEventsRepository, trashesThresholdMapper, buildingsRepository,
                trashDetailsMapper, trashesRepository, eventsRepository, zonesRepository, usersRepository,
                trashMapper
        );
    }

    @Test
    void findAll() {
        //Given
        List<Trashes> trashes = generateTrashesEntitiesList();
        List<TrashesThreshold> trashesThresholds = generateTrashesThresholdsEntitiesList();

        //When
        given(trashesRepository.findAll()).willReturn(trashes);
        given(trashesThresholdsRepository.findAllThresholds()).willReturn(trashesThresholds);

        TrashesAndBuildingsOnMapDTO response = trashesService.findAll();

        //Then
        then(response.getTrashes().size()).isEqualTo(1);
        then(response.getTrashes().get(0).getTrashId()).isEqualTo(2);
        then(response.getTrashes().get(0).getTrashesThreshold().get(0).getColor()).isEqualTo(trashesThresholds.get(0).getColor());
        then(response.getTrashes().get(0).getTrashesThreshold().get(0).getMaxOcuppation()).isEqualTo(trashesThresholds.get(0).getMaxOcuppation());
        then(response.getTrashes().get(0).getCapacity()).isEqualTo(trashes.get(1).getCapacity());
        then(response.getBuildings().size()).isEqualTo(1);
        then(response.getBuildings().get(0).getId()).isEqualTo(1);
        then(response.getBuildings().get(0).getName()).isEqualTo(trashes.get(0).getBuildings().getName());
        then(response.getBuildings().get(0).getTrashesCount()).isEqualTo(1);
    }

    @Test
    void findAllByZonesId() {
        //Given
        List<Trashes> trashes = generateTrashesEntitiesList();

        //When
        given(trashesRepository.findByZonesIdAndBuildingsInZonesId(Long.parseLong("1"))).willReturn(trashes);

        List<TrashesDTO> response = trashesService.findAllByZonesId(Long.parseLong("1"));

        //Then
        then(response.size()).isEqualTo(2);
        then(response.get(0).getTrashId()).isEqualTo(trashes.get(0).getId());
        then(response.get(0).getZone()).isEqualTo(trashes.get(0).getZones());
        then(response.get(1).getTrashId()).isEqualTo(trashes.get(1).getId());
        then(response.get(1).getZone()).isEqualTo(trashes.get(1).getZones());
    }

    @Test
    void findAllByBuildingId() {
        //Given
        List<Trashes> trashes = generateTrashesEntitiesList();
        trashes.remove(1);

        List<TrashesThreshold> trashesThresholds = generateTrashesThresholdsEntitiesList();

        //When
        given(trashesRepository.findByBuildingId(Long.parseLong("1"))).willReturn(trashes);
        given(trashesThresholdsRepository.findAllThresholds()).willReturn(trashesThresholds);

        List<TrashesReduceDTO> response = trashesService.findAllByBuildingId(Long.parseLong("1"));

        //Then
        then(response.size()).isEqualTo(1);
        then(response.get(0).getBuildingName()).isEqualTo(trashes.get(0).getBuildings().getName());
        then(response.get(0).getCapacity()).isEqualTo(trashes.get(0).getCapacity());
        then(response.get(0).getTrashesStatus()).isEqualTo(trashes.get(0).getTrashesStatus());
    }

    @Test
    void findTrashById() {
        //Given
        List<Trashes> trashes = generateTrashesEntitiesList();

        //When
        given(trashesRepository.findByTrashId(Long.parseLong("1"))).willReturn(trashes.get(0));
        given(buildingsRepository.findBuildingNameByTrashId(any())).willReturn("Test local description");

        TrashDetailsDTO response = trashesService.findTrashById(Long.parseLong("1"));

        //Then
        then(response.getCapacity()).isEqualTo(trashes.get(0).getCapacity());
        then(response.getTrashDescription()).isEqualTo(trashes.get(0).getDescription());
        then(response.getTrashesStatus()).isEqualTo(trashes.get(0).getTrashesStatus());
        then(response.getOccupation()).isEqualTo(trashes.get(0).getOccupation());
    }

    @Test
    void findListOfTrashes() {
        //Given
        List<Trashes> trashes = generateTrashesEntitiesList();
        List<TrashesThreshold> trashesThresholds = generateTrashesThresholdsEntitiesList();
        List<TrashesDTO> trashesDTOList = generateTrashesDtoList();

        //When
        given(trashesThresholdsRepository.findAllThresholds()).willReturn(trashesThresholds);
        given(trashesRepository.findAll()).willReturn(trashes);

        List<TrashesDTO> response = trashesService.findListOfTrashes();

        //Then
        then(response.size()).isEqualTo(2);
    }

    private List<Trashes> generateTrashesEntitiesList() {
        List<Trashes> trashes = new ArrayList<>();
        trashes.add(Trashes.builder()
                .id(1)
                .buildings(Buildings.builder()
                        .id(1)
                        .name("Building test")
                        .zones(Zones.builder()
                                .id(1)
                                .name("Zone test")
                                .build())
                        .build())
                .brand("Brand test")
                .trashType(TrashType.builder()
                        .id(2)
                        .name("Dry")
                        .build())
                .trashesStatus(TrashesStatus.builder()
                        .id(4)
                        .name("Ativa")
                        .description("A lixeira está em funcionamento")
                        .build())
                .capacity(50.0)
                .occupation(80.0)
                .build());
        trashes.add(Trashes.builder()
                .id(2)
                .brand("Brand test 2")
                .trashType(TrashType.builder()
                        .id(1)
                        .name("Organic")
                        .build())
                .zones(Zones.builder()
                        .id(1)
                        .name("Zone test")
                        .build())
                .capacity(50.0)
                .occupation(80.0)
                .build());

        return trashes;
    }

    private List<TrashesDTO> generateTrashesDtoList() {
        List<TrashesDTO> trashes = new ArrayList<>();
        trashes.add(TrashesDTO.builder()
                .trashId(1)
                .building(Buildings.builder()
                        .id(1)
                        .name("Building test")
                        .zones(Zones.builder()
                                .id(1)
                                .name("Zone test")
                                .build())
                        .build())
                .brand("Brand test")
                .trashType(TrashType.builder()
                        .id(2)
                        .name("Dry")
                        .build())
                .trashesStatus(TrashesStatus.builder()
                        .id(4)
                        .name("Ativa")
                        .description("A lixeira está em funcionamento")
                        .build())
                .capacity(50.0)
                .occupation(80.0)
                .trashesThreshold(generateTrashesThresholdsDtoList())
                .build());
        trashes.add(TrashesDTO.builder()
                .trashId(2)
                .brand("Brand test 2")
                .trashType(TrashType.builder()
                        .id(1)
                        .name("Organic")
                        .build())
                .zone(Zones.builder()
                        .id(1)
                        .name("Zone test")
                        .build())
                .capacity(50.0)
                .occupation(80.0)
                .trashesThreshold(generateTrashesThresholdsDtoList())
                .build());

        return trashes;
    }

    private List<TrashesThreshold> generateTrashesThresholdsEntitiesList() {
        List<TrashesThreshold> trashesThresholds = new ArrayList<>();
        trashesThresholds.add(TrashesThreshold.builder()
                .id(1)
                .trashes(generateTrashesEntitiesList().get(0))
                .maxOcuppation(16.8)
                .color("GREEN")
                .build());
        trashesThresholds.add(TrashesThreshold.builder()
                .id(2)
                .trashes(generateTrashesEntitiesList().get(0))
                .maxOcuppation(43.2)
                .color("YELLOW")
                .build());
        trashesThresholds.add(TrashesThreshold.builder()
                .id(3)
                .trashes(generateTrashesEntitiesList().get(0))
                .maxOcuppation(9999999.99)
                .color("RED")
                .build());
        trashesThresholds.add(TrashesThreshold.builder()
                .id(1)
                .trashes(generateTrashesEntitiesList().get(1))
                .maxOcuppation(16.8)
                .color("GREEN")
                .build());
        trashesThresholds.add(TrashesThreshold.builder()
                .id(2)
                .trashes(generateTrashesEntitiesList().get(1))
                .maxOcuppation(43.2)
                .color("YELLOW")
                .build());
        trashesThresholds.add(TrashesThreshold.builder()
                .id(3)
                .trashes(generateTrashesEntitiesList().get(1))
                .maxOcuppation(9999999.99)
                .color("RED")
                .build());

        return trashesThresholds;
    }

    private List<TrashesThresholdDTO> generateTrashesThresholdsDtoList() {
        List<TrashesThresholdDTO> trashesThresholds = new ArrayList<>();
        trashesThresholds.add(TrashesThresholdDTO.builder()
                .id(1)
                .maxOcuppation(16.8)
                .color("GREEN")
                .build());
        trashesThresholds.add(TrashesThresholdDTO.builder()
                .id(2)
                .maxOcuppation(43.2)
                .color("YELLOW")
                .build());
        trashesThresholds.add(TrashesThresholdDTO.builder()
                .id(3)
                .maxOcuppation(9999999.99)
                .color("RED")
                .build());

        return trashesThresholds;
    }

}