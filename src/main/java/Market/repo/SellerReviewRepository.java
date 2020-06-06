package Market.repo;

import Market.model.SellerReviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerReviewRepository extends JpaRepository<SellerReviews, Long> {
}
