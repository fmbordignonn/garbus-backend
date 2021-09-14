package pucrs.ages.garbus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pucrs.ages.garbus.dtos.ZonesDTO;
import pucrs.ages.garbus.entities.Zones;
import pucrs.ages.garbus.mappers.ZonesMapper;
import pucrs.ages.garbus.repositories.ZonesRepository;

import java.text.ParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ZonesService {

    private final ZonesMapper mapper;
    private final ZonesRepository repository;

    public List<ZonesDTO> findAll() {
        List<ZonesDTO> zonesDTOS = mapper.entityToDTO(repository.findAll());
        zonesDTOS.forEach(this::insertCounts);
        return zonesDTOS;
    }

    public ZonesDTO findById(Long id) {
        Zones source = repository.findById(id).orElse(null);
        ZonesDTO zonesDTO = mapper.entityToDTO(source);
        if (source != null) {
            insertCounts(zonesDTO);
        }
        return zonesDTO;
    }

    public void save(final ZonesDTO zonesDTO) throws ParseException {
        Zones zones = mapper.dtoToEntity(zonesDTO);
        repository.saveAndFlush(zones);
    }

    public void deleteById(Long id) throws ParseException {
        repository.deleteById(id);
    }

    private int countBuildingsByIdZone(Long zoneId) {
        return repository.countBuildingsByIdZone(zoneId);
    }

    private int countTrashesByIdZone(Long zoneId) {
        return repository.countTrashesByIdZone(zoneId);
    }

    private void insertCounts(ZonesDTO zones) {
        zones.setBuildingsCount(countBuildingsByIdZone(zones.getId()));
        zones.setTrashesCount(countTrashesByIdZone(zones.getId()));
    }
    
}
