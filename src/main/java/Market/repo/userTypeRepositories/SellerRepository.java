package Market.repo.userTypeRepositories;

import Market.model.userTypes.Seller;
import org.springframework.data.repository.CrudRepository;

public interface SellerRepository extends CrudRepository<Seller, Long> {
    Seller findByUsername(String username);
}
