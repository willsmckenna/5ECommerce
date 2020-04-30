package Market.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User {
    //Default User HAS A type buyer
    public User() { this.UT = new Buyer(); }

    @Id
    private Long UID;
    private String name;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserType UT;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    private List<ShippingAddress> Address;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PaymentInfo> paymentInfo;

    /*Login, logout, & manage re-direct them to the page of their type */
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
        Set (or reset) strategy
     */
    public void setUserType(UserType newType)
    {
        if(newType instanceof Seller) {
            newType = new Seller(this);
        }
        else if(newType instanceof Buyer) {
            //could do an overloaded copy Constructor
            newType = new Buyer();
        }
    }
}
