package pucrs.ages.garbus.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pucrs.ages.garbus.dtos.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/trashes")
public interface TrashesController {

    @GetMapping
    @ApiOperation("Find all trashes and buildings on map")
    ResponseEntity<TrashesAndBuildingsOnMapDTO> findAll();

    @GetMapping("/list")
    @ApiOperation("Find all trashes")
    ResponseEntity<List<TrashesDTO>> findListOfTrashes();

    @GetMapping("/zone/{zoneId}")
    @ApiOperation("Find all trashes on zone")
    ResponseEntity<List<TrashesDTO>> findAllByZone(@PathVariable Long zoneId);

    @GetMapping(path = "{trashId}/errors")
    @ApiOperation("Find errors by trash id")
    ResponseEntity<List<TrashesDTO>> findErrorsByTrashId(@NotNull @PathVariable("trashId") String trashId);

    @PostMapping(path = "/send-problem-report")
    @ApiOperation("Send problem report")
    ResponseEntity<Object> insertErrorInTrash(@RequestBody @Valid TrashProblemReportDTO trashProblemReport, Authentication authentication);

    @PostMapping(path = "/reactivate")
    @ApiOperation("Reactivate trash, removing from maintenance status")
    ResponseEntity<Object> reactivate(@RequestBody @Valid TrashReactivateDTO trashReactivateDTO, Authentication authentication);

    @GetMapping("/building/{buildingId}")
    @ApiOperation("Find trashes by building")
    ResponseEntity<List<TrashesReduceDTO>> findAllByBuilding(@PathVariable Long buildingId);

    @GetMapping("/{trashId}")
    @ApiOperation("Find trash by trashId")
    ResponseEntity<TrashDetailsDTO> findTrashById(@PathVariable Long trashId);

    @DeleteMapping("/{trashId}")
    @ApiOperation("Delete trash by trashId")
    ResponseEntity<TrashesDTO> deleteTrashById(@NotNull @PathVariable Long trashId);

    @PutMapping("/{trashId}")
    @ApiOperation("Update trash by trashId")
    ResponseEntity<TrashesDTO> updateTrashById(@NotNull @PathVariable Long trashId,
                                               @RequestBody TrashesDTO trashesDTO);

    @GetMapping("/status/{statusId}")
    @ApiOperation("Find all trashes by status id")
    ResponseEntity<TrashesAndBuildingsOnMapDTO> findAllByStatus(@NotNull @PathVariable("statusId") Long statusId);

    @PostMapping
    @ApiOperation("Save trashes")
    ResponseEntity<TrashesDTO> saveTrashes(@RequestBody @Valid TrashesDTO trashesDTO);

    @PostMapping("/report")
    @ApiOperation("Trash report")
    ResponseEntity<Object> report(@RequestBody @Valid @NotEmpty TrashReportDTO trashReportDTO);

    @GetMapping("/{trashId}/events")
    @ApiOperation("Find all events by trashes id")
    ResponseEntity<List<TrashesEventsDTO>> findAllEventsByTrashId(@NotNull @PathVariable Long trashId);
}
