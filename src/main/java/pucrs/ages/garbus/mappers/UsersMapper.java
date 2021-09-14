package pucrs.ages.garbus.mappers;

import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pucrs.ages.garbus.dtos.EventsDTO;
import pucrs.ages.garbus.dtos.UsersDTO;
import pucrs.ages.garbus.entities.Events;
import pucrs.ages.garbus.entities.Users;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Mapper(componentModel = "spring")
public class UsersMapper {

    private final ModelMapper modelMapper;

    public List<UsersDTO> mapear(List<Users> source) {
        return source
                .stream()
                .map(entity -> modelMapper.map(entity, UsersDTO.class))
                .collect(Collectors.toList());
    }

    public UsersDTO mapear(Users source) {
        return modelMapper.map(source, UsersDTO.class);
    }

    public UsersDTO mapear(Optional<Users> source) {
        return modelMapper.map(source, UsersDTO.class);
    }
}

