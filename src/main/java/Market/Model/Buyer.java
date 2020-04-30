package Market.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@Data
@EqualsAndHashCode(callSuper=false)
public class Buyer extends UserType {
    private ShoppingCart cart;
    private ArrayList<Orders> orders = new ArrayList<>();

    @Override
    public void manageProfile() {

    }

    @Override
    public void login() {
    }

    @Override
    public void logout() {
    }
}
