package pucrs.ages.garbus.mappers;

import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pucrs.ages.garbus.dtos.TrashesEventsDTO;
import pucrs.ages.garbus.entities.TrashesEvents;
import pucrs.ages.garbus.entities.Users;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Mapper(componentModel = "spring")
public class TrashesEventsMapper {

    private final ModelMapper modelMapper;

    public List<TrashesEventsDTO> mapear(List<TrashesEvents> source) {
        return source
                .stream()
                .map(entity -> {
                    Users user = entity.getUsers();
                    String login = user == null ? null : user.getLogin();
                    return TrashesEventsDTO
                        .builder()
                        .id(entity.getId())
                        .event(entity.getEvents())
                        .login(login)
                        .date(entity.getData())
                        .occupation(entity.getOccupation())
                        .others(entity.getOthers())
                        .build();
                })
                .sorted((e1, e2) -> e2.getDate().compareTo(e1.getDate()))
                .collect(Collectors.toList());
    }

    public TrashesEventsDTO mapear(TrashesEvents source) {
        return modelMapper.map(source, TrashesEventsDTO.class);
    }

}

