package Market.repo;

import Market.model.Product;
import Market.model.userTypes.Seller;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Set<Product> findBySeller(Seller seller);
}
