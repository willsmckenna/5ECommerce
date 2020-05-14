package Market.Model;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;

@TypeDefs({
        @TypeDef(name="hstore", typeClass= PostgreSQLHStoreType.class)
})

@Data
@Entity
@Table(name = "products")
@ToString

public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PID;

    //@ManyToOne
    //@JoinColumn(name = "products_for_sale")
    //Seller seller;

    private String name;
    private double quantity; //<= we may need a quantity of identical products because of a set in seller
    private double price;
    private String description;
    private Blob image;


    //shopping cart id for each product
    @ManyToOne
    @JoinColumn(name = "in_shopping_cart", nullable = false)
    private ShoppingCart shoppingCart;

    //order id for each product
   /* @ManyToOne
    @JoinColumn(name = "under_order", nullable = false)
    private Orders order; */

    public Product() {
    }

    public void removeProduct(){}
    public void updateProduct(){}

}
