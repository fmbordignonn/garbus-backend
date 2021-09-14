package pucrs.ages.garbus.sample;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.text.ParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final SampleMapper mapper;
    private final SampleRepository repository;


    public List<SampleDTO> findAll() {
        List<SampleEntity> sampleEntities = repository.findAll();

        return mapper.entityToDTO(sampleEntities);
    }

    public SampleDTO findById(Long id) {
        SampleEntity source = repository.findById(id).orElse(null);

        return mapper.entityToDTO(source);
    }

    public void save(final SampleDTO SampleDTO) throws ParseException {
        SampleEntity SampleEntity = mapper.dtoToEntity(SampleDTO);

        repository.save(SampleEntity);
    }


}
