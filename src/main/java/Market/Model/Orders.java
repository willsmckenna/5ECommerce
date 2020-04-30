package Market.Model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name= "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;

    @ManyToOne
    Buyer buyer;

    @OneToMany(
            //mapped by order, product will not create extra table
            mappedBy="order",
            cascade = CascadeType.ALL,
            fetch= FetchType.LAZY
    )
    @ToString.Exclude
    private List<Product> product_list;

}
