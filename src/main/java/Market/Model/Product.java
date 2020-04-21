package Market.Model;

import lombok.Data;

import java.sql.Blob;

@Data
public class Product {
    private int PID;
    private double price;
    private String name;
    private String description;
    private Blob image;
}
