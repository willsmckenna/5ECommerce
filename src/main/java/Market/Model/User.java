package Market.Model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User implements Serializable {
    @Id
    private Long UID;
    private String name;
    @OneToMany(
            mappedBy="shippingAddress",
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
