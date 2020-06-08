package Market.model.buyerRelated;

import Market.model.products.Product;
import Market.model.products.ProductsNoDepend;
import Market.model.userTypes.Buyer;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Table(name = "orders", schema = "public")
@TypeDefs({
        @TypeDef(name = "hstore", typeClass = PostgreSQLHStoreType.class)
})
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BuyerID")
    Buyer buyer;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    Set<ProductsNoDepend> ordersProducts = new HashSet<ProductsNoDepend>();


    private double orderTotal;

    public Orders() { }

    public Orders(Buyer buyer)
    {
        this.buyer = buyer;
    }

    public void addProduct(Product product, int quantity)
    {
        ProductsNoDepend productsNoDepend = new ProductsNoDepend(
                this,
                product.getId(),
                product.getSeller().getUsername(),
                product.getName(),
                product.getPrice(),
                quantity,
                product.getImage()
        );
        this.ordersProducts.add(productsNoDepend);
    }

    //No-SQL for order status
    @Type(type="jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.EAGER)
    private OrderTracking orderTrackingContents = new OrderTracking();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Double.compare(orders.orderTotal, orderTotal) == 0 &&
                Objects.equals(id, orders.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderTotal);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", orderTotal=" + orderTotal +
                '}';
    }
}
