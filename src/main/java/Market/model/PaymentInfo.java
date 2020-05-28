package Market.model;

import Market.model.userTypes.Users;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
public class PaymentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userPaymentId;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private Users user;

    public PaymentInfo(){}
    public PaymentInfo (Users user)
    {
        this.user = user;
    }

    private int creditCardNum;
    private int cvv;
    private Date expirationDate;


}