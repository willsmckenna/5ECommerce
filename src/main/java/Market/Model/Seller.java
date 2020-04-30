package Market.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Seller extends UserType
{

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Product> productsForSale;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserType> bannedUsers;

    /* Forcing a no-use default constructor is wishful thinking*/
    public Seller(User boundUser) {
        this.user = boundUser;
        productsForSale = null;
        bannedUsers = null;
    }
    public Seller(){
        this.user = null;
        this.productsForSale = null;
        bannedUsers = null;
    }

    @Override
    public void manageProfile() { }

    @Override
    public void login() { }

    @Override
    public void logout() { }

    @Override
    public void blockUser(UserType user)
    {
        if(this.bannedUsers== null)
        {
            this.bannedUsers = new HashSet<UserType>();
        }
        this.bannedUsers.add(user);
    }

    @Override
    protected void unBlockUser(UserType user) {
        if(this.bannedUsers != null)
        {
            this.bannedUsers.remove(user);
        }
    }

    public void listAnItem(Product prod)
    {
        if(productsForSale == null) {
            productsForSale = new HashSet<Product>();
        }
        productsForSale.add(prod);
    }

    public void removeAnItem(Product prod)
    {
        if(productsForSale != null) {
            productsForSale.remove(prod);
        }
    }


    public void sellAnItem(Buyer buyer, Product prod)
    {
        //Check if User is in the block list and update product inventory
        if(this.bannedUsers == null  || (!this.bannedUsers.contains(buyer.user)))
        {
            //can sell to this buyer
        }
    }
}
