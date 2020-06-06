package Market.model.userTypes;

import Market.model.messages.Message;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@TypeDefs({
        @TypeDef(name="hstore", typeClass = JsonBinaryType.class)
})

@Entity
@Data
@Table(name = "admin", schema = "public")
public class Admin implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @Column(name = "last_message_id")
    private Integer lastMessageKey = 0;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "messages")
    @Basic(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Message> messages = new ArrayList<Message> ();

    public Admin(String username, String firstname, String lastname) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Admin() {
    }

    public void addMessage(Message message)
    {
        if(message != null)
        {
            message.setId(this.lastMessageKey++);
            this.messages.add(message);
        }
    }
}
