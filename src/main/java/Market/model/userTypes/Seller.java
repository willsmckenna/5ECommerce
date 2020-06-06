package Market.model.userTypes;

import Market.model.PaymentInfo;
import Market.model.Product;
import Market.model.buyerRelated.ShippingAddress;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Product> productsForSale;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private Set<ShippingAddress> shippingAddress;

    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL)
    private Set<PaymentInfo> paymentInfo;

    @Type(type = "hstore")
    @Column(columnDefinition = "hstore")
    private Map<String, String> reviews = new HashMap<String, String>();

    public Seller() { }

    public Seller(String username, String firstname, String lastname) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return Objects.equals(id, seller.id) &&
                Objects.equals(username, seller.username) &&
                Objects.equals(firstname, seller.firstname) &&
                Objects.equals(lastname, seller.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, firstname, lastname);
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
