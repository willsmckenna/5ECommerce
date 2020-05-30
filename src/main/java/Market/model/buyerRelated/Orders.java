package Market.model.buyerRelated;

import Market.model.Product;
import Market.model.userTypes.Buyer;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Table(name = "orders", schema = "public")
@TypeDefs({
        @TypeDef(name = "hstore", typeClass = PostgreSQLHStoreType.class)
})
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BuyerID")
    Buyer buyer;

    //turn this into strings
   @OneToMany(orphanRemoval = true)
   Set<Product> products = new HashSet<Product>();


    private double orderTotal;

    public Orders() { }

    public Orders(Buyer buyer)
    {
        this.buyer = buyer;
    }



    //no-sql for order status
    @Type(type = "hstore")
    @Column(columnDefinition = "hstore")
    private Map<String,String> status = new HashMap<String,String>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Double.compare(orders.orderTotal, orderTotal) == 0 &&
                Objects.equals(id, orders.id) &&
                Objects.equals(products, orders.products) &&
                Objects.equals(status, orders.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, products, orderTotal, status);
    }
}
