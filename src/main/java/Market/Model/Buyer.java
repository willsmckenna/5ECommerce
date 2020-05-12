package Market.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
public class Buyer extends UserType
{
    //@OneToOne(mappedBy = "buyer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //private ShoppingCart cart = new ShoppingCart();

   // @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private Set<Orders> orders = new HashSet<Orders>();

    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private Set<UserType> bannedUsers = new HashSet<UserType>();

    /*
        Trying to enforce a no-default constructor
     */
    public Buyer()
    {
        this.user =null;
    }

    public Buyer(Users user)
    {
        this.user = user;
    }

   /* @Override
    protected void blockUser(UserType user) {
        if(this.bannedUsers == null)
        {
            this.bannedUsers = new HashSet<UserType>();
        }
        this.bannedUsers.add(user);
    }

    @Override
    protected void unBlockUser(UserType user) {
        if(this.bannedUsers != null && this.bannedUsers.contains(user))
        {
            this.bannedUsers.remove(user);
        }
    }

    @Override
    public void manageProfile() {
    }

    @Override
    public void login() {
    }

    @Override
    public void logout() {
    }*/
}
