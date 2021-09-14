package pucrs.ages.garbus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pucrs.ages.garbus.dtos.UsersDTO;
import pucrs.ages.garbus.dtos.UsersRequestDTO;
import pucrs.ages.garbus.entities.Profiles;
import pucrs.ages.garbus.entities.Users;
import pucrs.ages.garbus.excpetion.NotFoundException;
import pucrs.ages.garbus.mappers.UsersMapper;
import pucrs.ages.garbus.mappers.ZonesMapper;
import pucrs.ages.garbus.repositories.ProfilesRepository;
import pucrs.ages.garbus.repositories.TrashesEventsRepository;
import pucrs.ages.garbus.repositories.UsersRepository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersService implements UserDetailsService {

    private final UsersMapper maptools;
    private final UsersRepository usersRepository;
    private final TrashesEventsRepository trashesEventsRepository;
    private final ProfilesRepository profilesRepository;
    private final ZonesService zonesService;
    private final ZonesMapper mapper;

    public List<Users> findAll() {
        return usersRepository.findAll();
    }


    public Optional<Users> findById(Long id) {
        return usersRepository.findById(id);
    }

    public Users findByLoginEquals(String login) {
        return usersRepository.findByLoginEquals(login);
    }

    public Users findByLogin(String login) {
        return usersRepository.findByLogin(login);
    }

    public Users save(Users users) {
        return usersRepository.save(users);
    }

    public Users save(UsersRequestDTO user) {
        return usersRepository.save(new Users(user));
    }

    @Transactional
    public void deleteUser (Long idUser) {
        trashesEventsRepository.deleteTrashesEventsByUsersId(idUser);
        usersRepository.deleteById(idUser);
    }

    public Users updateUser (Long idUser, UsersRequestDTO usersRequestDTO) throws ParseException {
        Users users = findUser(idUser);
        if(users.getZone() != null && users.getZone().getId() != usersRequestDTO.getZone().getId()) {
            users.setZone(usersRequestDTO.getZone());
        }
        users.updateBy(usersRequestDTO);
        return usersRepository.save(users);
    }

    public Users findUserById (Long idUser) {
        return findUser(idUser);
    }

    private Users findUser(Long idUser) {
        return usersRepository.findById(idUser).orElseThrow(() -> new IllegalArgumentException("Error"));
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Users user = usersRepository.findByLogin(login);

        return new User(user.getLogin(), user.getPassword(), new ArrayList<>());
    }
}