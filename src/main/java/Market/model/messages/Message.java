package Market.model.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Message implements Serializable
{
    private Integer id;
    private String from;
    private String to;
    private String subject;
    private String messagePayload;
    private Date date;
}
