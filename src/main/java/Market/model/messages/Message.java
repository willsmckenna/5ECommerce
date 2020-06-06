package Market.model.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Message implements Serializable
{
    private String fromUsername;
    private String toUsername;
    private String subject;
    private String messagePayload;
    private Date date;
}
