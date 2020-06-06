package Market.repo;

import Market.model.userTypes.Buyer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends CrudRepository<Buyer, Long> {
    Buyer findByUsername(String username);
    boolean existsByUsername(String username);
}
