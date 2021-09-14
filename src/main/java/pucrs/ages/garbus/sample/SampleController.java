package pucrs.ages.garbus.sample;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sample")
public class SampleController {

    @Resource
    private SampleService service;

    @PostMapping
    @ApiOperation("Save a new sample")
    public ResponseEntity<Void> save(@RequestBody SampleDTO sampleDTO) {
        try {
            System.out.println(sampleDTO);
            service.save(sampleDTO);
        } catch (ParseException pe) {
            return new ResponseEntity<>(null, BAD_REQUEST);
        }
        return new ResponseEntity<>(null, CREATED);
    }

    @GetMapping
    @ApiOperation("Find all samples")
    public ResponseEntity<List<SampleDTO>> findAll() {
        return new ResponseEntity<>(service.findAll(), OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Find sample by id")
    public ResponseEntity<SampleDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), OK);
    }
}
