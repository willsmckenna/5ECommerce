package Market.Model;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Data
@Table(name= "orders")
@TypeDefs({
        @TypeDef(name = "hstore", typeClass = PostgreSQLHStoreType.class)
})
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;

    //@ManyToOne
    //Buyer buyer;

    @OneToMany(
            //mapped by order, product will not create extra table
            mappedBy="order",
            cascade = CascadeType.ALL,
            fetch= FetchType.LAZY
    )
    @ToString.Exclude
    private List<Product> product_list;


    //no-sql for order status
    @Type(type = "hstore")
    @Column(columnDefinition = "hstore")
    private Map<String,String> status = new HashMap<String,String>();
}
