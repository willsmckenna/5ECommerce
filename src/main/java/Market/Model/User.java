package Market.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    private Long UID;
    private String name;
    @OneToMany(
            mappedBy="userId",
            cascade = CascadeType.ALL,
            fetch= FetchType.LAZY
    )
    private List<ShippingAddress> Address;

    @OneToMany(
            mappedBy="userId",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<PaymentInfo> paymentInfo;

    //public void login() {} (must call the)
    //public void logout() {}
    //public void manageProfile(){};

   // private UserType UT; //the type this user can be

    public void setUserType()
    {
        //set userType to either Buyer or seller here
    }
}
