package Market.model.userTypes;

import Market.model.buyerRelated.PaymentInfo;
import Market.model.buyerRelated.Orders;
import Market.model.buyerRelated.ShippingAddress;
import Market.model.buyerRelated.ShoppingCart;
import Market.model.messages.Message;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@TypeDefs({
        @TypeDef(name="hstore", typeClass = JsonBinaryType.class)
})

@Data
@Entity
@Table(name = "buyer", schema = "public")
public class Buyer implements Serializable {

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

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "messages")
    @Basic(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Message> messages = new ArrayList<Message> ();

    @Column(name = "last_message_id")
    private Integer lastMessageKey = 0;

    public Buyer(String username, String firstname, String lastname) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Buyer() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buyer buyer = (Buyer) o;
        return Objects.equals(id, buyer.id) &&
                Objects.equals(username, buyer.username) &&
                Objects.equals(firstname, buyer.firstname) &&
                Objects.equals(lastname, buyer.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, firstname, lastname);
    }

    public void addMessage(Message message)
    {
        if(message != null)
        {
            message.setId(this.lastMessageKey++);
            this.messages.add(message);
        }
    }

}
