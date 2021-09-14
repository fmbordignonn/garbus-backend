package pucrs.ages.garbus.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import pucrs.ages.garbus.dtos.UsersDTO;
import pucrs.ages.garbus.dtos.UsersRequestDTO;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RequestMapping("/users")
public interface UserController {

    @GetMapping("/list")
    @ApiOperation("Find all users")
    List<UsersDTO> findAll();

    @PostMapping
    @ApiOperation("Save user")
    UsersDTO saveUser(@RequestBody @Valid UsersRequestDTO usersDTO);

    @DeleteMapping("/{idUser}")
    @ApiOperation("Delete user")
    void deleteUser(@PathVariable("idUser") Long idUser);

    @GetMapping("/{idUser}")
    @ApiOperation("Find user by id")
    UsersDTO findUserById(@PathVariable("idUser") Long idUser);

    @PutMapping("/{idUser}")
    @ApiOperation("Update user")
    UsersDTO updateUser(@RequestBody @Valid UsersRequestDTO usersDTO, @PathVariable Long idUser) throws ParseException;
}
