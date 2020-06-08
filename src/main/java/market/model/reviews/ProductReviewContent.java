package market.model.reviews;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductReviewContent implements Serializable {
   private String author;
   private String sellerUsername;
   private String productName;
   private String reviewMessage;
}
