package Market.Model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
public class PaymentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userPaymentId;

    //@ManyToOne                    --Leave for now - Peter
    //@JoinColumn(name = "UserID")
    //private Users user;

    private int creditCardNum;
    private int cvv;
    private Date expirationDate;


}
