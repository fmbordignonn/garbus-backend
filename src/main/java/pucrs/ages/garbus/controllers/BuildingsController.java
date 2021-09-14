package pucrs.ages.garbus.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pucrs.ages.garbus.dtos.BuildingsDTO;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/buildings")
public interface BuildingsController {

    @GetMapping
    @ApiOperation("Find all Buildings")
    ResponseEntity<List<BuildingsDTO>> findAll();

    @GetMapping("/{id}")
    @ApiOperation("Find Building by id")
    ResponseEntity<BuildingsDTO> findAllById(@PathVariable ("id") @NotNull Long id);

    @PostMapping
    @ApiOperation("Create Building")
    ResponseEntity saveBuilding(@RequestBody @NotNull BuildingsDTO BuildingsDTO);

    @PutMapping()
    @ApiOperation("Update Building")
    ResponseEntity updateBuilding(@RequestBody @NotNull BuildingsDTO BuildingsDTO);

    @DeleteMapping("/{id}")
    @ApiOperation("Delete Building By Id")
    ResponseEntity deleteBuilding(@PathVariable("id") @NotNull Long id);

}
