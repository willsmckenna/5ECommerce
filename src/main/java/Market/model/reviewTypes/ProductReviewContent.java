package Market.model.reviewTypes;

import Market.model.userTypes.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductReviewContent implements Serializable {
    private String author;
    private String sellerUsername;
    private String productName;
    private String reviewMessage;
}
