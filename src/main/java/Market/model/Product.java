package Market.model;

import Market.model.userTypes.Seller;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Objects;

@TypeDefs({
        @TypeDef(name="hstore", typeClass= PostgreSQLHStoreType.class)
})

@Data
@Entity
@Table(name = "products", schema = "public")
@ToString
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "products_for_sale")
    Seller seller;

    private String name;
    private double quantity; //<= we may need a quantity of identical products because of a set in seller
    private double price;
    private String description;
    private Blob image;

    public Product() {
    }

    public Product(Seller seller) {
        this.seller = seller;
    }

    public void removeProduct(){}
    public void updateProduct(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.quantity, quantity) == 0 &&
                Double.compare(product.price, price) == 0 &&
                Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, price, description);
    }
}
