package pucrs.ages.garbus.mappers;

import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pucrs.ages.garbus.dtos.BuildingsDTO;
import pucrs.ages.garbus.entities.Buildings;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Mapper(componentModel = "spring")
public class BuildingsMapper {

    private final ModelMapper modelMapper;


    public Buildings mapearDTO(BuildingsDTO BuildingsDTO) throws ParseException {
        return modelMapper.map(BuildingsDTO, Buildings.class);
    }


    public List<Buildings> mapearDTO(List<BuildingsDTO> source) {
        return source
                .stream()
                .map(entity -> modelMapper.map(entity, Buildings.class))
                .collect(Collectors.toList());
    }


    public BuildingsDTO mapear(Buildings Buildings) {
        return modelMapper.map(Buildings, BuildingsDTO.class);
    }
    public List<BuildingsDTO> mapear(List<Buildings> source) {
        return source
                .stream()
                .map(entity -> modelMapper.map(entity, BuildingsDTO.class))
                .collect(Collectors.toList());
    }


    public BuildingsDTO entityToDTO(Buildings buildings) {
        return modelMapper.map(buildings, BuildingsDTO.class);
    }

    
}

