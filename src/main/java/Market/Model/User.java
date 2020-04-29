package Market.Model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@ToString
public abstract class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UID;

    private String name;
    @OneToMany(
            mappedBy="userId",
            cascade = CascadeType.ALL,
            fetch= FetchType.LAZY
    )
    @ToString.Exclude
    private List<ShippingAddress> Address;

    @OneToMany(
                mappedBy="userId",
                cascade = CascadeType.ALL,
                fetch = FetchType.EAGER
    )
    @ToString.Exclude
    private List<PaymentInfo> paymentInfo;

    public void login() {}
    public void logout() {}
    public abstract void manageProfile();
}
