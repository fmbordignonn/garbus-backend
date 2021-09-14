package pucrs.ages.garbus.mappers;

import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pucrs.ages.garbus.dtos.TypesEventsDTO;
import pucrs.ages.garbus.entities.Events;
import pucrs.ages.garbus.entities.TypesEvents;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Mapper(componentModel = "spring")
public class TypesEventsMapper {

    private final ModelMapper modelMapper;

    public List<TypesEventsDTO> mapearEventsToTypeEvents(List<Events> source) {
        return source
                .stream()
                .map(entity -> modelMapper.map(entity.getTypesEvents(), TypesEventsDTO.class))
                .collect(Collectors.toList());
    }

    public TypesEventsDTO mapEntityEventToDTO(Events events) {
        return modelMapper.map(events.getTypesEvents(), TypesEventsDTO.class);
    }

    public TypesEvents mapDtoToEntity(TypesEventsDTO typesEventsDTO) {
        return modelMapper.map(typesEventsDTO, TypesEvents.class);
    }

    public TypesEventsDTO mapearTypeEvents(Optional<TypesEvents> typesEvents) {
        return modelMapper.map(typesEvents, TypesEventsDTO.class);
    }

    public List<TypesEventsDTO> mapearTypeEvents(List<TypesEvents> source) {
        return source
                .stream()
                .map(entity -> modelMapper.map(entity, TypesEventsDTO.class))
                .collect(Collectors.toList());
    }
}

