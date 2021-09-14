package pucrs.ages.garbus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pucrs.ages.garbus.entities.TrashesStatus;
import pucrs.ages.garbus.repositories.TrashesStatusRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrashesStatusService {

    private final TrashesStatusRepository trashesStatusRepository;

    public List<TrashesStatus> findAll() {
        return trashesStatusRepository.findAll();
    }

    public Optional<TrashesStatus> findById(Long id) {
        return trashesStatusRepository.findById(id);
    }
}