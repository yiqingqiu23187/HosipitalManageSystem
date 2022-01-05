package fudan.se.lab2.repository;

import fudan.se.lab2.domain.Bed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BedRepository extends CrudRepository<Bed, Long> {
    Iterable<Bed> findByArea(int area);
    Bed findByBedid(Long bedid);
}
