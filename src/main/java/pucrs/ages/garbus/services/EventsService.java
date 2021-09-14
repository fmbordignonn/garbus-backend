package pucrs.ages.garbus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pucrs.ages.garbus.dtos.EventsDTO;
import pucrs.ages.garbus.dtos.TypesEventsDTO;
import pucrs.ages.garbus.entities.Events;
import pucrs.ages.garbus.mappers.EventsMapper;
import pucrs.ages.garbus.repositories.EventsRepository;

import javax.annotation.Resource;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventsService {

    @Value("${events.problem_status.reactivated}")
    private char statusReactivated;

    @Value("${events.problem_status.error_others}")
    private char statusErrorOther;

    @Resource
    private TypesEventsService typesEventsService;
    private final EventsMapper eventsMapper;
    private final EventsRepository eventsRepository;

    public List<EventsDTO> findAll() {
        return eventsMapper.mapear(eventsRepository.findAll());
    }

    public List<TypesEventsDTO> findAllErrorTypeEvent() {
        return typesEventsService.findAllErrorTypeEvent();
    }

    public Events findErrorEventById(Long id) {
        return eventsRepository.findEventsByProblemStatusEqualsAndTypesEventsId(typesEventsService.statusError(), id);
    }

    public Events findOtherErrorEvent() {
        return eventsRepository.findEventsByProblemStatusEquals(statusErrorOther).get(0);
    }

    public Events findEventByProblemStatusReactivate() {
        return eventsRepository.findEventsByProblemStatusEquals(statusReactivated).get(0);
    }

}
