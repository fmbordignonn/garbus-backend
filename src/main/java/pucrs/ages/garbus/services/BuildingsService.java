package pucrs.ages.garbus.services;

import pucrs.ages.garbus.mappers.ZonesMapper;
import pucrs.ages.garbus.repositories.BuildingsRepository;
import pucrs.ages.garbus.mappers.BuildingsMapper;
import org.springframework.stereotype.Service;
import pucrs.ages.garbus.dtos.BuildingsDTO;
import pucrs.ages.garbus.entities.Buildings;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.text.ParseException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BuildingsService {

    private final BuildingsMapper maptools;
    private final BuildingsRepository repository;
    private final TrashesService trashesService;
    private final ZonesService zonesService;
    private final ZonesMapper zonesMapper;

    public List<BuildingsDTO> findAll() {
        List<BuildingsDTO> buildingsDTOS = maptools.mapear(repository.findAll());
        buildingsDTOS.forEach(this::setTrashesCount);
        return buildingsDTOS;
    }
    
    public BuildingsDTO findById(Long id) {
        Buildings source = repository.findById(id).orElse(null);
        BuildingsDTO buildingsDTO = maptools.entityToDTO(source);
        setTrashesCount(buildingsDTO);
        return buildingsDTO;
    }

    public BuildingsDTO save(final BuildingsDTO buildingsDTO) throws ParseException {
        Buildings buildings = maptools.mapearDTO(buildingsDTO);
        buildings.setZones(zonesMapper.dtoToEntity(zonesService.findById(buildingsDTO.getZones().getId())));
        return maptools.mapear(repository.saveAndFlush(buildings));
    }

    public void deleteById(Long id) throws ParseException {
        repository.deleteById(id);
    }

    public void setTrashesCount(BuildingsDTO buildingsDTO) {
        if(!Objects.isNull(buildingsDTO)) {
            buildingsDTO.setTrashesCount(trashesService.getTrashesCountByBuilding(buildingsDTO.getId()));
        }
    }

}