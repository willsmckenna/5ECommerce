package Market.Repo;

import Market.Model.PaymentInfo;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepo extends CrudRepository<PaymentInfo,Long> {
}
