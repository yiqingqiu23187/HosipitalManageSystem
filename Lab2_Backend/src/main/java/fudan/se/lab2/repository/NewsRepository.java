package fudan.se.lab2.repository;

import fudan.se.lab2.domain.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends CrudRepository<News,Long> {
    Iterable<News> findByArea(int area);
    News findByNewsid(Long newsid);
}
