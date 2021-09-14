package pucrs.ages.garbus.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pucrs.ages.garbus.controllers.BuildingsController;
import pucrs.ages.garbus.dtos.BuildingsDTO;
import pucrs.ages.garbus.excpetion.BadRequestException;
import pucrs.ages.garbus.services.BuildingsService;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class BuildingsControllerImpl implements BuildingsController {

    @Resource
    BuildingsService buildingsService;

    @Override
    public ResponseEntity<List<BuildingsDTO>> findAll() {
        return new ResponseEntity<>(buildingsService.findAll(), OK);
    }

    @Override
    public ResponseEntity<BuildingsDTO> findAllById(Long id) {
        BuildingsDTO buildingsDTO = buildingsService.findById(id);
        return buildingsDTO != null
                ? new ResponseEntity<>(buildingsDTO, OK)
                : new ResponseEntity<>(NO_CONTENT);
    }

    @Override
    public ResponseEntity saveBuilding (BuildingsDTO buildingsDTO) {
        buildingsDTO.setId(0L);
        return getResponseEntity(buildingsDTO);
    }

    @Override
    public ResponseEntity updateBuilding(BuildingsDTO buildingsDTO) {
        return getResponseEntity(buildingsDTO);
    }

    @Override
    public ResponseEntity deleteBuilding(Long id) {
        try {
            buildingsService.deleteById(id);
            return new ResponseEntity<>(OK);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity getResponseEntity(BuildingsDTO buildingsDTO) {
        try {
            buildingsDTO = buildingsService.save(buildingsDTO);
            return new ResponseEntity<>(buildingsDTO, CREATED);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
        } catch (BadRequestException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }

}
