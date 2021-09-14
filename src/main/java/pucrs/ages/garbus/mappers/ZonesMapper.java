package pucrs.ages.garbus.mappers;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pucrs.ages.garbus.dtos.ZonesDTO;
import pucrs.ages.garbus.entities.Zones;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
@Mapper(componentModel = "spring")
public class ZonesMapper {

    private final ModelMapper modelMapper;


    public Zones dtoToEntity(ZonesDTO ZonesDTO) throws ParseException {
        return modelMapper.map(ZonesDTO, Zones.class);
    }


    public List<Zones> dtoToEntity(List<ZonesDTO> source) {
        return source
                .stream()
                .map(entity -> modelMapper.map(entity, Zones.class))
                .collect(Collectors.toList());
    }


    public ZonesDTO entityToDTO(Zones Zones) {
        return modelMapper.map(Zones, ZonesDTO.class);
    }
    public List<ZonesDTO> entityToDTO(List<Zones> source) {
        return source
                .stream()
                .map(entity -> modelMapper.map(entity, ZonesDTO.class))
                .collect(Collectors.toList());
    }
}
