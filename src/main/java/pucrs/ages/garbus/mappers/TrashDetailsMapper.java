package pucrs.ages.garbus.mappers;

import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import pucrs.ages.garbus.dtos.TrashDetailsDTO;
import pucrs.ages.garbus.entities.Trashes;
import pucrs.ages.garbus.entities.TrashesThreshold;

import java.util.List;

@Component
@AllArgsConstructor
@Mapper(componentModel = "spring")
public class TrashDetailsMapper {
    private final TrashesThresholdMapper trashesThresholdMapper;

    public TrashDetailsDTO mapToDTO(Trashes trash, String zoneDescription, List<TrashesThreshold> trashesThresholds) {
        trashesThresholds.forEach(trashesThreshold -> trashesThreshold.setTrashes(null));
        return TrashDetailsDTO.builder()
                .capacity(trash.getCapacity())
                .occupation(trash.getOccupation())
                .trashDescription(trash.getDescription())
                .trashesStatus(trash.getTrashesStatus())
                .localDescription(zoneDescription)
                .trashesThreshold(trashesThresholdMapper.mapToDTO(trashesThresholds))
                .build();
    }
}
