package Market.Model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import java.io.Serializable;
import java.sql.Blob;

@Data
@Entity
@Table(name = "products")
@ToString

public abstract class Product implements Serializable {

//public class Product {
    @Id
    private Long PID;

    private String name;
    private double price;
    private String description;
    private Blob image;
}
