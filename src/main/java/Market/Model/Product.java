package Market.Model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;

@Data
@Entity
@Table(name = "products")
@ToString

public abstract class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PID;

    @ManyToOne
    @JoinColumn(name = "products_for_sale")
    Seller seller;

    private String name;
    private int quantity; //<= we may need a quantity of identical products because of a set in seller
    private double price;
    private String description;
    private Blob image;

    //shopping cart id for each product
    @ManyToOne
    @JoinColumn(name = "in_shopping_cart", nullable = false)
    private ShoppingCart shoppingCart;

    //order id for each product
    @ManyToOne
    @JoinColumn(name = "under_order", nullable = false)
    private Orders order;

    protected Product() {
    }

    public void removeProduct(){}
    public void updateProduct(){}

}
