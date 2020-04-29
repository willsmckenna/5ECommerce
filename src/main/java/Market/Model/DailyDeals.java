package Market.Model;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "DailyDeal")

public class DailyDeals {

    @Id
    private Long Date;

    private ArrayList<Product> dealProducts = new ArrayList<>();

    public void removeDeal(){}
    public void addDeal(){}


}
