package Market.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Data
@Entity
public class PaymentInfo {
    @Id
    private int creditCardNum;
    @JoinColumn(name = "user_id", nullable=false)
    private int userId;
}
