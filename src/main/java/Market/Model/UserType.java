package Market.Model;

import lombok.Data;
import javax.persistence.*;

/*
    This abstract class acts as an interface for both buyer and seller, but has the added
    Effect of being able to store the core-base class info that many tables rely on
 */

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class UserType {
    @Id
    public int userTypeID;

    @OneToOne
    public Users user;

    protected void blockUser(UserType user){}
    protected void unBlockUser(UserType user){};
    protected void login() {}
    protected void logout() {}
    protected void manageProfile(){}
}
