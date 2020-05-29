package Market.model.userTypes;

import Market.model.PaymentInfo;
import Market.model.buyerRelated.Orders;
import Market.model.buyerRelated.ShippingAddress;
import Market.model.buyerRelated.ShoppingCart;
import lombok.Data;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "buyer", schema = "public")
public class Buyer{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private Set<ShippingAddress> shippingAddress = new HashSet<ShippingAddress>();

    @OneToOne(mappedBy = "buyer", cascade = CascadeType.ALL)
    private ShoppingCart cart;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Orders> orders = new HashSet<Orders>();

    @OneToMany(mappedBy = "buyer",cascade = CascadeType.ALL)
    private Set<PaymentInfo> paymentInfo = new HashSet<PaymentInfo>();


    public Buyer(String username, String firstname, String lastname) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Buyer() {
    }

}
