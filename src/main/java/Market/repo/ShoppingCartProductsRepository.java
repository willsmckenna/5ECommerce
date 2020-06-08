package Market.repo;

import Market.model.products.ShoppingCartProducts;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingCartProductsRepository extends CrudRepository<ShoppingCartProducts, Long> {
}
