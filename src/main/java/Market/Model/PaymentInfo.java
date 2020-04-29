package Market.Model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.sql.Date;

@Data
@Entity
public class PaymentInfo {

    @Id
    private int creditCardNum;
    private int cvv;
    private Date expirationDate;
    //@OneToOne
    @JoinColumn(name = "user_id", nullable=false)
    private int userId;
}
