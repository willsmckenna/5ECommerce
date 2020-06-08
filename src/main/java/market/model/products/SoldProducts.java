package market.model.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoldProducts  implements Serializable {
    private String sellerUsername;
    private String buyerUsername;
    private String itemName;
    private Date soldDate;
    private double itemPrice;
    private int itemQuantity;
}
