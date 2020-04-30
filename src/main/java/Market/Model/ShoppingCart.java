package Market.Model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "shoppingcart")
public class ShoppingCart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartID;

    @OneToOne
    Buyer buyer;

    @OneToMany(
            //mapped by shopping_cart, product will not create extra table
            mappedBy="shoppingCart",
            cascade = CascadeType.ALL,
            fetch= FetchType.LAZY
    )
    @ToString.Exclude
    private List<Product> itemsInCart;



    public void removeItem(){}
    public void addItemToCart(){}
    public void checkOut(){}

}
