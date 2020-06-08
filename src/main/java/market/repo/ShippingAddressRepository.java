package market.repo;

import market.model.buyerRelated.ShippingAddress;
import market.model.userTypes.Buyer;
import market.model.userTypes.Seller;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ShippingAddressRepository extends CrudRepository<ShippingAddress, Long> {
    Set<ShippingAddress> findBySeller(Seller seller);
    Set<ShippingAddress> findByBuyer(Buyer buyer);
}
