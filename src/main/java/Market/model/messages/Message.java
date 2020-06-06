package Market.model.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class Message implements Serializable
{
    private String fromUsername;
    private String toUsername;
    private String subject;
    private String messagePayload;
    private Date date;

    public Message()
    {
        this.date = new Date();
    }
}
