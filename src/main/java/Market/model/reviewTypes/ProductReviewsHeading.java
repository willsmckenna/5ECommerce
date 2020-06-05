package Market.model.reviewTypes;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@TypeDefs({
        @TypeDef(name="jsonb", typeClass= JsonBinaryType.class)
})
@Entity
@Data
public class ProductReviewsHeading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type="jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.EAGER)
    private Set<ProductReviewContent> productReviewContents = new HashSet<ProductReviewContent>();
}
