package fudan.se.lab2.repository;

import fudan.se.lab2.domain.User;

import jdk.nashorn.internal.runtime.arrays.IteratorAction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author LBW
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserid(Long userid);
    User findByUsername(String username);
    Iterable<User> findByAreaAndIdentity(int area,int identity);
}
