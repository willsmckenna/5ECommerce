package market.repo;

import market.model.buyerRelated.PaymentInfo;
import market.model.userTypes.Buyer;
import market.model.userTypes.Seller;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PaymentRepo extends CrudRepository<PaymentInfo,Long> {
    Set<PaymentInfo> findByBuyer(Buyer buyer);
    Set<PaymentInfo> findBySeller(Seller seller);
}
