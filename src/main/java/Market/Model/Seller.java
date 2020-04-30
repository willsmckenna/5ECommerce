package Market.Model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
@Data
@Entity
public class Seller extends UserType
{

    public Seller(User boundUser)
    {
        this.user = boundUser;
    }
    //private ArrayList<Product> selling = new ArrayList<>();

    public void listAnItem(){}
    public void removeAnItem(){}
    public void blockBuyer(){}

    @Override
    public void manageProfile() { }

    @Override
    public void login() { }

    @Override
    public void logout() { }
}
