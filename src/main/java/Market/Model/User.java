package Market.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {
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


    public void login() {}
    public void logout() {}
    public abstract void manageProfile();
}
