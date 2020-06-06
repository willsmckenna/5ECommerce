package Market.model.buyerRelated;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Blob;

@Data
@Entity
@Table(name = "products_no_depend",schema = "public")
public class ProductsNoDepend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ToString.Exclude
    @ManyToOne
    Orders orders;

    @Column(name = "product_id")
    Long product_id;

    @Column(name ="seller_username")
    String seller_username;

    @Column(name = "description")
    private String description;

    @Column(name = "product_name")
    String product_name;

    @Column(name ="quantity")
    private int quantity;

    @Column(name = "price")
    private double price;


    @Column(name ="image")
    Blob image;


    public ProductsNoDepend() {
    }

    public ProductsNoDepend(Orders orders, Long product_id, String seller_username, String product_name,
                            double price, int quantity, Blob image)
    {
        this.orders = orders;
        this.product_id =product_id;
        this.seller_username =seller_username;
        this.product_name =product_name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }
}
