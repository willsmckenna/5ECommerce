package Market.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class UserType {
    @Id
    public int userTypeID;

    @OneToOne
    public User user;

    public void login() {}
    public void logout() {}
    public void manageProfile(){}
}
