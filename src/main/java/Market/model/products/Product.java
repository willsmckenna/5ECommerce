package Market.model.products;

import Market.model.reviews.Review;
import Market.model.userTypes.Seller;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
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
    private String seedImageStr;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb",name="reviews")
    @Basic(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Review> reviews = new ArrayList<>();

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
            seller_1.setFirstname("NULL");
            seller_1.setLastname("NULL");
            return seller_1;
        }
        return seller;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, price, description);
    }

}
