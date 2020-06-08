package market.repo;

import market.model.userTypes.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {
    Users findByUsername(String username);
    Set<Users> findByUsernameLike(String username);
}
