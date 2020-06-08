package Market.model.reviews;
import Market.model.userTypes.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review implements Serializable {
    private String author;
    private Date date;
    private String content;
}