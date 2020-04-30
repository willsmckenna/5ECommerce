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
    private Set<UserType> bannedBuyers;

    /* Forcing a no-use default constructor is wishful thinking*/
    public Seller(User boundUser) {
        this.user = boundUser;
        productsForSale = null;
        bannedBuyers = null;
    }
    public Seller(){
        this.user = null;
        this.productsForSale = null;
        bannedBuyers = null;
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
        if(this.bannedBuyers == null)
        {
            this.bannedBuyers = new HashSet<UserType>();
            this.bannedBuyers.add(user);
        }
    }

    @Override
    protected void unBlockUser(UserType user) {
        if(this.bannedBuyers != null)
        {
            this.bannedBuyers.remove(user);
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




    public void sellAnItem(Buyer buy, Product prod)
    {
        //Check if User is in the block list and update product inventory
    }
}
