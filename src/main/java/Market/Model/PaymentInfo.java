package Market.Model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
public class PaymentInfo {

    @Id
    private Long userPaymentId;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private Users user;

    private int creditCardNum;
    private int cvv;
    private Date expirationDate;


}
