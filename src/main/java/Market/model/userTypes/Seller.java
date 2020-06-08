package Market.model.userTypes;

import Market.model.buyerRelated.PaymentInfo;
import Market.model.products.Product;
import Market.model.products.SoldProducts;
import Market.model.reviews.Review;
import Market.model.buyerRelated.ShippingAddress;
import Market.model.messages.Message;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@TypeDefs({
        @TypeDef(name="hstore", typeClass= PostgreSQLHStoreType.class)
})
@Data
@Entity
@Table(name = "seller", schema = "public")
public class Seller implements Serializable  {

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
    private Set<Product> productsForSale = new HashSet<>();

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private Set<ShippingAddress> shippingAddress = new HashSet<>();

    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL)
    private Set<PaymentInfo> paymentInfo = new HashSet<>();

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb",name="reviews")
    @Basic(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Review> reviews = new ArrayList<>();

    @Column(name = "last_message_id")
    private Integer lastMessageKey = 0;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "messages")
    @Basic(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Message> messages = new ArrayList<Message> ();

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "sold_items")
    @Basic(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SoldProducts> soldProducts = new ArrayList<SoldProducts> ();

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

    public void addMessage(Message message)
    {
        if(message != null)
        {
            message.setId(this.lastMessageKey++);
            this.messages.add(message);
        }
    }

    public void addSoldProduct(Product product, String buyerUsername)
    {
        if(product != null)
        {
           SoldProducts soldProducts = new SoldProducts();
           soldProducts.setSellerUsername(this.username);
           soldProducts.setBuyerUsername(buyerUsername);
           soldProducts.setItemName(product.getName());
           soldProducts.setItemPrice(product.getPrice());
           soldProducts.setSoldDate(new Date());
           soldProducts.setItemQuantity(1);
           this.soldProducts.add(soldProducts);
        }
    }
}
