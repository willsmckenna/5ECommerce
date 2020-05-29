package Market.model.buyerRelated;

import Market.model.userTypes.Buyer;
import Market.model.userTypes.Seller;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
public class ShippingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userShippingId;

    @ManyToOne
    private Buyer buyer;

    @ManyToOne
    private Seller seller;

    private String shippingAddress;

    public ShippingAddress(){ }
    public ShippingAddress(Buyer buyer ){ this.buyer = buyer;}

    public ShippingAddress(Seller seller ){ this.seller = seller;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShippingAddress that = (ShippingAddress) o;
        return Objects.equals(userShippingId, that.userShippingId) &&
                Objects.equals(shippingAddress, that.shippingAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userShippingId, shippingAddress);
    }
}
