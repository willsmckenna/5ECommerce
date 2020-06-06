package Market.repo;

import Market.model.SellerReviews;
import Market.model.userTypes.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findByUsername(String username);
    boolean existsByUsername(String username);
}
