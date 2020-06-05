package Market.repo;

import Market.model.reviewTypes.ProductReviewsHeading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewRepository extends JpaRepository<ProductReviewsHeading, Long> {

}
