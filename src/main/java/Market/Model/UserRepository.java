package Market.Model;

import Market.Model.UserType;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserType, Long> {
}
