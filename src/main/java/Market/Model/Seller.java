package Market.Model;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@TypeDefs({
        @TypeDef(name="hstore", typeClass= PostgreSQLHStoreType.class)
})
@Data
@Entity
public class Seller extends UserType
{

    //@OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //private Set<Product> productsForSale;

    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private Set<UserType> bannedUsers;

    /* Forcing a no-use default constructor is wishful thinking*/
    public Seller(Users boundUser) {
        this.user = boundUser;
    }
    public Seller(){
        this.user = null;
    }

    @Override
    public void manageProfile() { }

    @Override
    public void login() { }

    @Override
    public void logout() { }
}
