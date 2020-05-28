package Market.model.buyerRelated;

import Market.model.Product;
import Market.model.userTypes.Buyer;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "shoppingcart")
public class ShoppingCart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartID;

    @OneToOne
    @JoinColumn(name = "BuyerID")
    Buyer buyer;

    @OneToMany
    Set<Product> products = new HashSet<Product>();

    public  ShoppingCart(){}
    public  ShoppingCart(Buyer buyer ) { this.buyer = buyer; }

    public void removeItem(){}
    public void addItemToCart(Product product){
        this.products.add(product);
    }
    public void checkOut(){}

}
