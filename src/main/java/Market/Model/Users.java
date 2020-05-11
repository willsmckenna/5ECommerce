package Market.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Users {

    //force the underlying DB to come up with the primary key, that is what AUTO does
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UID;
    public String name;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserType UT;

    /*Worry about Shipping address & Payment Later*/

    //@OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    //private List<ShippingAddress> Address;

    //@OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //private List<PaymentInfo> paymentInfo;

    //Default User HAS A type buyer
    public Users() {
        this.UT = new Buyer(this);
    }

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
