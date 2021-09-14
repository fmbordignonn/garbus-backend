package pucrs.ages.garbus.sample;

import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;
import java.text.ParseException;
import java.util.List;


@Component
@RequiredArgsConstructor
@Mapper(componentModel = "spring")
public class SampleMapper {

    private final ModelMapper modelMapper;


    public SampleEntity dtoToEntity(SampleDTO sampleDTO) throws ParseException {
        return modelMapper.map(sampleDTO, SampleEntity.class);
    }


    public List<SampleEntity> dtoToEntity(List<SampleDTO> source) {
        return source
                .stream()
                .map(entity -> modelMapper.map(entity, SampleEntity.class))
                .collect(Collectors.toList());
    }


    public SampleDTO entityToDTO(SampleEntity sampleEntity) {
        return modelMapper.map(sampleEntity, SampleDTO.class);
    }
    public List<SampleDTO> entityToDTO(List<SampleEntity> source) {
        return source
                .stream()
                .map(entity -> modelMapper.map(entity, SampleDTO.class))
                .collect(Collectors.toList());
    }
}
