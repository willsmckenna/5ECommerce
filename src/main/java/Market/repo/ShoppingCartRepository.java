package Market.repo;

import Market.model.buyerRelated.ShoppingCart;
import Market.model.userTypes.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findByBuyer(Buyer buyer);
}
