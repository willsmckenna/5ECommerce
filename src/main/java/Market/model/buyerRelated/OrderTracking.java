package Market.model.buyerRelated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderTracking implements Serializable {
    private long OrderID;
    private String status;
}
