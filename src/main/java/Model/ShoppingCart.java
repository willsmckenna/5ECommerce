package Model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ShoppingCart {
    private int cartID;
    private ArrayList<Product> itemsInCart = new ArrayList<>();

    public void removeItem(){}
    public void addItemToCart(){}
    public void purchaseItems(){}
}
