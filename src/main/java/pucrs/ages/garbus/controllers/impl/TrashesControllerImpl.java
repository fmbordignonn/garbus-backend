package pucrs.ages.garbus.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pucrs.ages.garbus.controllers.TrashesController;
import pucrs.ages.garbus.dtos.*;
import pucrs.ages.garbus.excpetion.BadRequestException;
import pucrs.ages.garbus.excpetion.NotFoundException;
import pucrs.ages.garbus.services.TrashesService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequiredArgsConstructor
public class TrashesControllerImpl implements TrashesController {

    @Resource
    private TrashesService trashesService;

    @Override
    public ResponseEntity<TrashesAndBuildingsOnMapDTO> findAll() {
        return new ResponseEntity<>(trashesService.findAll(), OK);
    }

    @Override
    public ResponseEntity<List<TrashesDTO>> findAllByZone(@PathVariable Long zoneId) {
        return new ResponseEntity<>(trashesService.findAllByZonesId(zoneId), OK);
    }

    @Override
    public ResponseEntity<List<TrashesDTO>> findListOfTrashes() {
        return new ResponseEntity<>(trashesService.findListOfTrashes(), OK);
    }

    @Override
    public ResponseEntity<List<TrashesDTO>> findErrorsByTrashId(String trashId) {
        return null;
    }

    @Override
    public ResponseEntity deleteTrashById(@PathVariable Long trashId) {
        try {
            return new ResponseEntity<>(trashesService.deleteTrashById(trashId), OK);
        } catch (BadRequestException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getError(), BAD_REQUEST);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getError(), NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> insertErrorInTrash(TrashProblemReportDTO trashProblemReport, Authentication authentication) {
        try {
            String login = authentication.getName();
            trashesService.insertErrorInTrash(trashProblemReport, login);
            return new ResponseEntity<>("Problema reportado com sucesso", OK);
        } catch (BadRequestException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getError(), BAD_REQUEST);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getError(), NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> reactivate(@Valid TrashReactivateDTO trashReactivateDTO, Authentication authentication) {
        try {
            String login = authentication.getName();
            trashesService.reactivate(trashReactivateDTO, login);
            return new ResponseEntity<>("Lixeira ativada com sucesso", OK);
        } catch (BadRequestException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getError(), BAD_REQUEST);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getError(), NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<TrashesReduceDTO>> findAllByBuilding(@PathVariable Long buildingId) {
        return new ResponseEntity<>(trashesService.findAllByBuildingId(buildingId), OK);
    }

    @Override
    public ResponseEntity<TrashDetailsDTO> findTrashById(@PathVariable Long trashId) {
        return new ResponseEntity<>(trashesService.findTrashById(trashId), OK);
    }

    @Override
    public ResponseEntity<TrashesAndBuildingsOnMapDTO> findAllByStatus(Long statusId) {
        return new ResponseEntity<>(trashesService.findAllByStatusId(statusId), OK);
    }

    @Override
    public ResponseEntity<TrashesDTO> saveTrashes(TrashesDTO trashesDTO) {
        trashesDTO.setTrashId(0L);
        return getResponseEntity(trashesDTO);
    }

    @Override
    public ResponseEntity updateTrashById(Long trashId, TrashesDTO trashesDTO) {
        try {
            return new ResponseEntity<>(trashesService.updateTrashById(trashId, trashesDTO), OK);
        } catch (ParseException | BadRequestException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getError(), NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity getResponseEntity(TrashesDTO trashesDTO) {
        try {
            trashesDTO = trashesService.save(trashesDTO);
            return new ResponseEntity<>(trashesDTO, CREATED);
        } catch (ParseException | BadRequestException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity report(TrashReportDTO trashReportDTO) {
        try {
            return new ResponseEntity<>(trashesService.report(trashReportDTO), OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getError(), BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity findAllEventsByTrashId(Long trashId) {
        try {
            return new ResponseEntity<>(trashesService.findAllEventsByTrashId(trashId), OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getError(), NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }

}


