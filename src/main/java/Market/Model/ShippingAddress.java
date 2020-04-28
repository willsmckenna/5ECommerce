package Market.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Entity
public class ShippingAddress implements Serializable {
    @Id
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    private String shippingAddress;
}
