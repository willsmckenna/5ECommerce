package Market.Model;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.ArrayList;
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

    @ManyToOne
    @JoinColumn(name = "BuyerID")
    Buyer buyer;

    public Orders()
    {
    }

    public Orders(Buyer buyer)
    {
        this.buyer = buyer;
    }


    //no-sql for order status
    @Type(type = "hstore")
    @Column(columnDefinition = "hstore")
    private Map<String,String> status = new HashMap<String,String>();
}
