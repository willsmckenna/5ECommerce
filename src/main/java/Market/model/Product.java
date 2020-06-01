package Market.model;

import Market.model.userTypes.Seller;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;
import lombok.Data;
import org.hibernate.annotations.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Blob;
import java.util.Objects;

@TypeDefs({
        @TypeDef(name="hstore", typeClass= PostgreSQLHStoreType.class)
})

@Data
@Entity
@Table(name = "products", schema = "public")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    Seller seller;

    private String name;
    private int quantity;
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

    public Seller getSeller() {
        if(seller == null)
        {
            Seller seller_1 = new Seller();
            seller_1.setUsername("VERY MUCH NULL");
            return seller_1;
        }
        return seller;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, price, description);
    }

}
