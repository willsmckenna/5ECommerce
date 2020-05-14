package Market.Model;


import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@TypeDefs({
        @TypeDef(name="hstore", typeClass= PostgreSQLHStoreType.class)
})
@Entity
@Data
public class Admin implements Serializable,IMessage {

    @Id
    @GeneratedValue
    private long adminID;

    private String name;


    @OneToMany(
            mappedBy="UID",
            cascade = CascadeType.ALL,
            fetch= FetchType.LAZY
    )
    @ToString.Exclude
    private List<Users> users; 


    @Type(type="hstore")
    @Column(columnDefinition = "hstore")
    private Map<String,String> messages = new HashMap<>();

    public void removeUser(){}
    public void removeSeller(){}
    public void removeItem(){}
    public void processRequest(){}

    @Override
    public void sendMsg() {

    }

    @Override
    public void showMsgs() {

    }
}
