package market.model.buyerRelated;

import market.model.products.Product;
import market.model.products.ShoppingCartProducts;
import market.model.userTypes.Buyer;
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

    @OneToMany(mappedBy = "shoppingCart",orphanRemoval = true, cascade = CascadeType.ALL )
    Set<ShoppingCartProducts> shoppingCartProducts = new HashSet<ShoppingCartProducts>();

    public  ShoppingCart(){

    }
    public  ShoppingCart(Buyer buyer ) { this.buyer = buyer; }


    public void addProduct(Product product) {
        this.shoppingCartProducts.add(new ShoppingCartProducts(this, product.getId()));
    }

    public void checkOut(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart that = (ShoppingCart) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
