package Market.Model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class alien {

    @Id
    private int id;
    private String name;


    @Override
    public String toString() {
        return "alien{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
