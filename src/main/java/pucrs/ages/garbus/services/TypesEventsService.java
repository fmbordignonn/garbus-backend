package pucrs.ages.garbus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pucrs.ages.garbus.dtos.TypesEventsDTO;
import pucrs.ages.garbus.mappers.TypesEventsMapper;
import pucrs.ages.garbus.repositories.EventsRepository;
import pucrs.ages.garbus.repositories.TypesEventsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypesEventsService {

    private final TypesEventsMapper typesEventsMapper;
    private final EventsRepository eventsRepository;
    private final TypesEventsRepository typesEventsRepository;

    @Value("${events.problem_status.error}")
    private char statusError;

    public List<TypesEventsDTO> findAll() {
        return typesEventsMapper.mapearTypeEvents(typesEventsRepository.findAll());
    }

    public TypesEventsDTO findById(Long id) {
        return typesEventsMapper.mapearTypeEvents(typesEventsRepository.findById(id));
    }

    public List<TypesEventsDTO> findAllErrorTypeEvent() {
        return typesEventsMapper.mapearEventsToTypeEvents(eventsRepository.findEventsByProblemStatusEquals(statusError()));
    }

    public void save(TypesEventsDTO typesEventsDTO) {
        typesEventsRepository.save(typesEventsMapper.mapDtoToEntity(typesEventsDTO));
    }

    public char statusError() {
        return statusError;
    }
}
