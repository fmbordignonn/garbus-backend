package pucrs.ages.garbus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pucrs.ages.garbus.entities.Profiles;

public interface ProfilesRepository extends JpaRepository<Profiles, Long> {
}
