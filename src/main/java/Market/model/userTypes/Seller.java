package Market.model.userTypes;

import Market.model.PaymentInfo;
import Market.model.Product;
import Market.model.buyerRelated.ShippingAddress;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;
import lombok.Data;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Set;

@TypeDefs({
        @TypeDef(name="hstore", typeClass= PostgreSQLHStoreType.class)
})
@Data
@Entity
@Table(name = "seller", schema = "public")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Product> productsForSale;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private Set<ShippingAddress> shippingAddress;

    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL)
    private Set<PaymentInfo> paymentInfo;

    public Seller() { }

    public Seller(String username, String firstname, String lastname) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
