package pucrs.ages.garbus.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pucrs.ages.garbus.dtos.BuildingsDTO;
import pucrs.ages.garbus.sample.SampleDTO;

import java.util.List;

@RequestMapping("/teste")
public interface TesteController {

    @PostMapping
    @ApiOperation("Save a new sample")
    ResponseEntity<Void> save(@RequestBody SampleDTO sampleDTO);

    @GetMapping
    @ApiOperation("Find all samples")
    ResponseEntity<List<BuildingsDTO>> findAll();

    @GetMapping("/{id}")
    @ApiOperation("Find sample by id")
    ResponseEntity<SampleDTO> findById(@PathVariable Long id);
}
