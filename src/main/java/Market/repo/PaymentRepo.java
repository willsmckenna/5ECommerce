package Market.repo;

import Market.model.buyerRelated.PaymentInfo;
import Market.model.userTypes.Buyer;
import Market.model.userTypes.Seller;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PaymentRepo extends CrudRepository<PaymentInfo,Long> {
    Set<PaymentInfo> findByBuyer(Buyer buyer);
    Set<PaymentInfo> findBySeller(Seller seller);
}
