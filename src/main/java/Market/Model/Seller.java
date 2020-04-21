package Market.Model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Seller extends User{
    private ArrayList<Product> selling = new ArrayList<>();

    public void listAnItem(){}
    public void removeAnItem(){}
    public void blockBuyer(){}

    @Override
    public void manageProfile() {

    }
}
