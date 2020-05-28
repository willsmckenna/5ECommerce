package Market.repo;

import Market.model.PaymentInfo;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepo extends CrudRepository<PaymentInfo,Long> {
}
