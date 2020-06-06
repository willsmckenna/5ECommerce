package Market.repo;

import Market.model.SellerReviews;
import Market.model.userTypes.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerReviewRepository extends JpaRepository<Seller, Long> {
}
