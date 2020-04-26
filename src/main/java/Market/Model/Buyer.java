package Market.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@Data
@EqualsAndHashCode(callSuper=false)
public class Buyer extends User {
    private ShoppingCart cart;
    private ArrayList<Order> orders = new ArrayList<>();

    @Override
    public void manageProfile() {

    }
}
