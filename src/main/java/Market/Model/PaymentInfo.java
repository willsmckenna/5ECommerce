package Market.Model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
public class PaymentInfo {

    @Id
    private int userId;

    @JoinColumn(name = "UID", nullable=false)
    @ManyToOne
    private User user;

    private int creditCardNum;
    private int cvv;
    private Date expirationDate;


}
