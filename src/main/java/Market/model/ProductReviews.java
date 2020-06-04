package Market.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.List;

@TypeDefs({
        @TypeDef(name="jsonb", typeClass= JsonBinaryType.class)
})
@Entity
@Data
public class ProductReviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ProductReviewId;
    @Type(type="jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.EAGER)
    private List<Review> reviews;
}
