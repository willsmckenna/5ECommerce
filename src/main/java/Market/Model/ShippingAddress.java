package Market.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class ShippingAddress {
    @Id
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    private String shippingAddress;
}
