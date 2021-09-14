package pucrs.ages.garbus.mappers;

import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pucrs.ages.garbus.dtos.SimplifiedTrashesWithThresholdsDTO;
import pucrs.ages.garbus.dtos.TrashesDTO;
import pucrs.ages.garbus.dtos.TrashesReduceDTO;
import pucrs.ages.garbus.dtos.TrashesThresholdDTO;
import pucrs.ages.garbus.entities.Trashes;
import pucrs.ages.garbus.entities.TrashesThreshold;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
@Mapper(componentModel = "spring")
public class SimplifiedTrashesWithThresholdsMapper {
    private final TrashesThresholdMapper trashesThresholdMapper;
    private final TrashesMapper trashesMapper;

    public List<TrashesReduceDTO> mapToDTO(List<Trashes> trashes, List<TrashesThreshold> trashesThresholds) {
        List<TrashesReduceDTO> trashesReduceDTOS = new ArrayList<>();
        TrashesReduceDTO trashesReduceDTO = new TrashesReduceDTO();
        for (Trashes trash : trashes) {
            List<TrashesThresholdDTO> tempTrashesThresholds = new ArrayList<>();
            getTrashThresholds(trashesThresholds, trash, tempTrashesThresholds);
            trashesReduceDTO = TrashesReduceDTO.builder()
                    .trashId(trash.getId())
                    .capacity(trash.getCapacity())
                    .occupation(trash.getOccupation())
                    .longitude(trash.getLongitude())
                    .latitude(trash.getLatitude())
                    .trashesStatus(trash.getTrashesStatus())
                    .trashDescription(trash.getDescription())
                    .buildingName(Objects.nonNull(trash.getBuildings()) ? trash.getBuildings().getName() : null)
                    .trashesThreshold(tempTrashesThresholds)
                    .build();
            trashesReduceDTOS.add(trashesReduceDTO);
        }
        return trashesReduceDTOS;
    }

    public List<TrashesDTO> mapToTrashesDTOWithThresholds(List<Trashes> trashes, List<TrashesThreshold> trashesThresholds) {
        List<TrashesDTO> trashesDTOS = new ArrayList<>();
        TrashesDTO trashesDTO = new TrashesDTO();
        for (Trashes trash : trashes) {
            List<TrashesThresholdDTO> tempTrashesThresholds = new ArrayList<>();
            getTrashThresholds(trashesThresholds, trash, tempTrashesThresholds);
            trashesDTO = trashesMapper.mapear(trash);
            trashesDTO.setTrashesThreshold(tempTrashesThresholds);
            trashesDTOS.add(trashesDTO);
        }
        return trashesDTOS;
    }

    private void getTrashThresholds(List<TrashesThreshold> trashesThresholds, Trashes trash, List<TrashesThresholdDTO> tempTrashesThresholds) {
        for (int i = 0; i < trashesThresholds.size(); i++) {
            if (trashesThresholds.get(i).getTrashes().getId() == trash.getId()) {
                trashesThresholds.get(i).setTrashes(null);
                tempTrashesThresholds.add(trashesThresholdMapper.mapToDTO(trashesThresholds.get(i)));
                trashesThresholds.remove(i);
                i--;
            }
        }
    }
}
