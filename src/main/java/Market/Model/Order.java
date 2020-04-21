package Market.Model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Order {
     private int orderID;
     private ArrayList<Product> orderList = new ArrayList<>();

     public void modifyOrder(){}
}
