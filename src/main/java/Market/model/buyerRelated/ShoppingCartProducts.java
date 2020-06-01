package Market.model.buyerRelated;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name= "shopping_cart_products", schema = "public")
public class ShoppingCartProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "product_Id")
    String productId;


    public ShoppingCartProducts() {
    }

    public ShoppingCartProducts(String productId) {
        this.productId = productId;
    }
}
