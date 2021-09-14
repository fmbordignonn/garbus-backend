package pucrs.ages.garbus.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pucrs.ages.garbus.controllers.EventsController;
import pucrs.ages.garbus.dtos.EventsDTO;
import pucrs.ages.garbus.dtos.TypesEventsDTO;
import pucrs.ages.garbus.services.EventsService;
import pucrs.ages.garbus.services.TypesEventsService;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequiredArgsConstructor
public class EventsControllerImpl implements EventsController {

    @Resource
    EventsService eventsService;

    @Resource
    TypesEventsService typesEventsService;

    @Override
    public ResponseEntity<List<EventsDTO>> findAll() {
        return new ResponseEntity<>(eventsService.findAll(), OK);
    }

    @Override
    public ResponseEntity<List<TypesEventsDTO>> findAllTypes() {
        return new ResponseEntity<>(typesEventsService.findAll(), OK);
    }

    @Override
    public ResponseEntity<List<TypesEventsDTO>> findAllTypesErrors() {
        return new ResponseEntity<>(eventsService.findAllErrorTypeEvent(), OK);
    }
}
