package Market.Model;


import javax.persistence.Entity;
import javax.persistence.Id;

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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
