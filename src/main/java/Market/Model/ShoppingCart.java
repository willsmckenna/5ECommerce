package Market.Model;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "shoppingcart")
public class ShoppingCart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartID;

    @OneToOne
    @JoinColumn(name = "BuyerID")
    Buyer buyer;

    public  ShoppingCart(){}
    public  ShoppingCart(Buyer buyer ) { this.buyer = buyer; }

    public void removeItem(){}
    public void addItemToCart(){}
    public void checkOut(){}

}
