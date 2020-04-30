package Market.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User {

    public User()
    {
        //Default User HAS A type buyer
        this.UT = new Buyer();
    }
    @Id
    private Long UID;
    private String name;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserType UT;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    private List<ShippingAddress> Address;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PaymentInfo> paymentInfo;

    public void login() {
        UT.login();
    }
    public void logout() {
        UT.logout();
    }
    public void manageProfile(){
        UT.manageProfile();
    }

    /*
        Set strategy
     */
    public void setUserType(UserType newType)
    {
        if(newType instanceof Seller)
        {
            //Could do an overloaded copy Constructor
            newType = new Seller(this);
        }
        else if(newType instanceof Buyer) {
            //could do an overloaded copy Constructor
            newType = new Buyer();
        }
    }
}
