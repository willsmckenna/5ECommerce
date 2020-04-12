package Model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Buyer extends User {
    private ShoppingCart cart;
    private ArrayList<Order> orders = new ArrayList<>();

    @Override
    public void manageProfile() {

    }
}
