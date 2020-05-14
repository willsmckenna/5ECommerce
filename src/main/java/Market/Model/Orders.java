package Market.Model;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Table(name= "orders")
@TypeDefs({
        @TypeDef(name = "hstore", typeClass = PostgreSQLHStoreType.class)
})
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long order_id;

    @ManyToOne
    @JoinColumn(name = "BuyerID")
    Buyer buyer;

   @OneToMany
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
}
