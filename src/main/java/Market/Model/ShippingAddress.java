package Market.Model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@ToString
public class ShippingAddress implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ShippingAddressID;

    @ManyToOne
    private User userId;

    private String shippingAddress;
}
