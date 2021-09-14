package pucrs.ages.garbus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pucrs.ages.garbus.dtos.ErrorResponse;
import pucrs.ages.garbus.dtos.TrashesEventsDTO;
import pucrs.ages.garbus.entities.*;
import pucrs.ages.garbus.excpetion.NotFoundException;
import pucrs.ages.garbus.mappers.TrashesEventsMapper;
import pucrs.ages.garbus.repositories.TrashesEventsRepository;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrashesEventsService {

    @Resource
    private EventsService eventsService;
    @Resource
    private UsersService usersService;
    @Resource
    private TrashesService trashesService;

    private final TrashesEventsRepository trashesEventsRepository;

    private final TrashesEventsMapper trashesEventsMapper;

    public void insertTrashProblemReport(Long trashId, Long typeEventId, String others, String login) throws NotFoundException {
        Users users = Optional.ofNullable(usersService.findByLoginEquals(login))
                .orElseThrow(() -> new NotFoundException(
                        new ErrorResponse("Não foi possível encontrar usuário logado para o id " + login)
                ));
        Trashes trashes = trashesService.findById(trashId)
                .orElseThrow(() -> new NotFoundException(
                        new ErrorResponse("Lixeira não encontrada para o id " + trashId)
                ));

        Events events = null;
        if (!Objects.isNull(typeEventId)) {
            events = Optional.ofNullable(eventsService.findErrorEventById(typeEventId))
                    .orElseThrow(() -> new NotFoundException(
                            new ErrorResponse("Tipo de evento não encontrado para o id " + typeEventId))
                    );
        } else {
            events = Optional.ofNullable(eventsService.findOtherErrorEvent())
                    .orElseThrow(() -> new NotFoundException(
                            new ErrorResponse("Tipo de evento OUTROS não encontrado")
                    ));
        }

        trashesEventsRepository.save(TrashesEvents.builder()
                .events(events)
                .trashes(trashes)
                .users(users)
                .others(others)
                .data(Calendar.getInstance().getTime())
                .build());
    }

    public void insertTrashReactivation(Long trashId, String login) throws NotFoundException {
        Users users = Optional.ofNullable(usersService.findByLoginEquals(login))
                .orElseThrow(() -> new NotFoundException(new ErrorResponse("Não foi possível encontrar usuário logado para o id " + login)));
        Trashes trashes = trashesService.findById(trashId)
                .orElseThrow(() -> new NotFoundException(new ErrorResponse("Lixeira não encontrada para o id " + trashId)));

        Events events = Optional.ofNullable(eventsService.findEventByProblemStatusReactivate()).orElseThrow(() -> new NotFoundException(new ErrorResponse("Evento de reativação não encontrado")));

        trashesEventsRepository.save(TrashesEvents.builder()
                .events(events)
                .trashes(trashes)
                .users(users)
                .data(Calendar.getInstance().getTime())
                .build());
    }


    public void deleteByTrashId(Long trashId) {
        trashesEventsRepository.deleteTrashesEventsByTrashesId(trashId);
    }

    public List<TrashesEventsDTO> findAllByTrashId(Long trashId) {
        List<TrashesEventsDTO> list = trashesEventsMapper.mapear(trashesEventsRepository.findAllByTrashesId(trashId));
        return list.isEmpty() ? null : list;
    }
}
