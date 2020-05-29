package Market.repo;

import Market.model.buyerRelated.Orders;
import Market.model.userTypes.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Long> {
    Orders findByBuyer(Buyer buyer);

}
