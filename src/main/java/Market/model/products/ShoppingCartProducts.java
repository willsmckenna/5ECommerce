package Market.model.products;

import Market.model.buyerRelated.ShoppingCart;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name= "shopping_cart_products", schema = "public")
public class ShoppingCartProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    ShoppingCart shoppingCart;

    @Column(name = "product_Id")
    Long productId;

//    @Column(name = "product_quantity")
//    Long quantity;

    public ShoppingCartProducts() {
    }

    public ShoppingCartProducts(ShoppingCart shoppingCart, Long productId) {
        this.shoppingCart = shoppingCart;
        this.productId = productId;
 //       this.quantity = quantity;
    }
}
