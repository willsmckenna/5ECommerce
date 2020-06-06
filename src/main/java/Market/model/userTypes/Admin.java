package Market.model.userTypes;


import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "admin", schema = "public")
public class Admin{

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    public Admin(String username, String firstname, String lastname) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Admin() {
    }
}
