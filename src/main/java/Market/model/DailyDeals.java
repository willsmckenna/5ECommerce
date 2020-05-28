package Market.model;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "DailyDeal")

public class DailyDeals implements Serializable {

    @Id
    private Long Date;

    private double originalPrice;
    private double discountPrice;


    @OneToMany(
            mappedBy="DailyDeal",
            cascade = CascadeType.ALL,
            fetch= FetchType.LAZY
    )

    public void removeDeal(){}
    public void addDeal(){}


}
