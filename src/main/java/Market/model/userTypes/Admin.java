package Market.model.userTypes;

import Market.model.messages.Message;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.*;

@TypeDefs({
        @TypeDef(name="hstore", typeClass= PostgreSQLHStoreType.class)
})

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

    @Type(type = "hstore")
    @Column(columnDefinition = "hstore",name = "messages")
    private Map<Integer, Message> messages = new HashMap<Integer, Message>();

    @Column(name = "last_message_key")
    private Integer lastKey = 0;

    public Admin(String username, String firstname, String lastname) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Admin() {
    }

    //pretty slick
    public void addMessage(Message message) {
        this.getMessages().put(lastKey, message);
        lastKey++;
    }

}
