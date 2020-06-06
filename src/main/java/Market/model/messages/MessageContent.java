package Market.model.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageContent implements Serializable {
    private String fromUsername;
    private String toUsername;
    private String subject;
    private String messagePayload;
}
