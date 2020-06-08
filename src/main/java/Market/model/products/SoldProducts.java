package Market.model.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoldProducts  implements Serializable {
    private String itemName;
    private String itemPrice;
    private String soldDate;
    private String itemQuantity;
}
