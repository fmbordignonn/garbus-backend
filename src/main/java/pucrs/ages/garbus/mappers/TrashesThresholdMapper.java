package pucrs.ages.garbus.mappers;

import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pucrs.ages.garbus.dtos.TrashesThresholdDTO;
import pucrs.ages.garbus.entities.Trashes;
import pucrs.ages.garbus.entities.TrashesThreshold;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Mapper(componentModel = "spring")
public class TrashesThresholdMapper {
    private final ModelMapper modelMapper;

    public TrashesThresholdDTO mapToDTO(TrashesThreshold trashesThreshold) {
        return modelMapper.map(trashesThreshold, TrashesThresholdDTO.class);
    }

    public List<TrashesThresholdDTO> mapToDTO(List<TrashesThreshold> source) {
        return source
                .stream()
                .map(entity -> modelMapper.map(entity, TrashesThresholdDTO.class))
                .collect(Collectors.toList());
    }

    public List<TrashesThreshold> mapToEntity(Trashes trashes, List<TrashesThresholdDTO> source) {
        return source
                .stream()
                .map(dto -> {
                    TrashesThreshold trashesThreshold = modelMapper.map(dto, TrashesThreshold.class);
                    trashesThreshold.setTrashes(trashes);
                    return trashesThreshold;
                })
                .collect(Collectors.toList());
    }
}
