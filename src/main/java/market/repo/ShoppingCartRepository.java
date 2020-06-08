package market.repo;

import market.model.buyerRelated.ShoppingCart;
import market.model.userTypes.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findByBuyer(Buyer buyer);
}
