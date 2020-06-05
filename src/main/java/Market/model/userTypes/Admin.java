package Market.model.userTypes;


import Market.model.messages.IMessage;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@TypeDefs({
        @TypeDef(name="hstore", typeClass= PostgreSQLHStoreType.class)
})
@Entity
@Data
@Table(name = "admin", schema = "public")
public class Admin implements Serializable, IMessage {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @Type(type="hstore")
    @Column(columnDefinition = "hstore")
    private Map<String,String> messages = new HashMap<>();


    public Admin(String username, String firstname, String lastname) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Admin() {
    }

    public void removeUser(){

    }
    public void removeItem(){

    }
    public void processRequest(){

    }

    @Override
    public void sendMsg() {
    }

    @Override
    public void showMsgs() {
    }
}
