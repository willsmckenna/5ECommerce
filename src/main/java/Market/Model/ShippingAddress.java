package Market.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ShippingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userShippingId;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private Users user;

    private String shippingAddress;

    public ShippingAddress(){ }
    public ShippingAddress(Users user ){ this.user = user;}
}
