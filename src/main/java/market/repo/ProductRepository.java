package market.repo;

import market.model.products.Product;
import market.model.userTypes.Seller;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Set<Product> findBySeller(Seller seller);
    Set<Product> findAllByNameLike(String name);
    Product getById(Long Id);
    Product findByNameAndSeller(String name, Seller seller);
}
