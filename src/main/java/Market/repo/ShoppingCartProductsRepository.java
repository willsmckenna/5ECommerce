package Market.repo;

import Market.model.buyerRelated.ShoppingCartProducts;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingCartProductsRepository extends CrudRepository<ShoppingCartProducts, Long> {
}
