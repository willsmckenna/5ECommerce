package market.repo;

import market.model.buyerRelated.Orders;
import market.model.userTypes.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Long> {
    Orders findByBuyer(Buyer buyer);

}
