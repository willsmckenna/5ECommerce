package Market.model.userTypes;

import Market.model.buyerRelated.Orders;
import Market.model.buyerRelated.ShoppingCart;
import lombok.Data;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name ="buyer", schema = "public")
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

    @OneToOne(mappedBy = "buyer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ShoppingCart cart = new ShoppingCart(this);

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Orders> orders = new HashSet<Orders>();

}
