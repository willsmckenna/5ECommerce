package Market.model.userTypes;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private int active;
    private String roles;
    private String permissions;


    public Users(String username, String password, String roles, String permissions) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.permissions = permissions;
        this.active = 1;
    }

    public Users(){}

    public List<String> getRoleList()
    {
        if(this.roles.length() >0)
        {
            return  Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList()
    {
        if(this.permissions.length() >0) {
            return  Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }

    /*Cannot be named isActive*/
    public boolean userIsActive() {
        return active > 0;
    }
}
