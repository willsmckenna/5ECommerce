package Market.model.buyerRelated;

import Market.model.userTypes.Buyer;
import Market.model.userTypes.Seller;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
public class PaymentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "buyer")
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name = "seller")
    private Seller seller;

    public PaymentInfo(){}

    private int creditCardNum;
    private int cvv;
    private Date expirationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentInfo that = (PaymentInfo) o;
        return creditCardNum == that.creditCardNum &&
                cvv == that.cvv &&
                Objects.equals(id, that.id) &&
                Objects.equals(expirationDate, that.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creditCardNum, cvv, expirationDate);
    }
}
