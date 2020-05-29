package Market.repo;

import Market.model.buyerRelated.ShippingAddress;
import Market.model.userTypes.Buyer;
import Market.model.userTypes.Seller;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ShippingAddressRepository extends CrudRepository<ShippingAddress, Long> {
    Set<ShippingAddress> findBySeller(Seller seller);
    Set<ShippingAddress> findByBuyer(Buyer buyer);
}
