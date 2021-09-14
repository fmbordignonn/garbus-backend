package pucrs.ages.garbus.mappers;

import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pucrs.ages.garbus.dtos.EventsDTO;
import pucrs.ages.garbus.entities.Events;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Mapper(componentModel = "spring")
public class EventsMapper {

    private final ModelMapper modelMapper;

    public List<EventsDTO> mapear(List<Events> source) {
        return source
                .stream()
                .map(entity -> modelMapper.map(entity, EventsDTO.class))
                .collect(Collectors.toList());
    }

    public EventsDTO mapear(Events source) {
        return modelMapper.map(source, EventsDTO.class);
    }

}

