package Market.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UID;
    public String name;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserType UT = new Buyer();

    // I can’t add users because most of the user class is commented out!
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    private List<ShippingAddress> Address = new ArrayList<ShippingAddress>();

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PaymentInfo> paymentInfo = new ArrayList<PaymentInfo>();

    //Default User HAS A type buyer
    public Users() { }

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
            newType = new Buyer(this);
        }
    }
}
