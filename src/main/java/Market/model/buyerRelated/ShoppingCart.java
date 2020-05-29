package Market.model.buyerRelated;

import Market.model.Product;
import Market.model.userTypes.Buyer;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Data
@Entity
@Table(name = "shoppingcart", schema = "public")
public class ShoppingCart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart that = (ShoppingCart) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, products);
    }
}
