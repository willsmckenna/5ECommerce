package Market.model.messages;

import Market.model.reviewTypes.ProductReviewContent;
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

/*
    THIS IS ALL WRONG - peter
 */
public class MessageHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Type(type="jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    private Set<MessageContent> messageContents = new HashSet<MessageContent>();

    public Set<MessageContent> findByAuthor(String username) {
        Set<MessageContent> messageContents = new HashSet<MessageContent>();
        for(MessageContent MC: this.messageContents ) {
            if(MC.getFromUsername().equals(username)) {
                messageContents.add(MC);
            }
        }
        return messageContents;
    }

    public Set<MessageContent> findByTo(String username)
    {
        Set<MessageContent> messageContents = new HashSet<MessageContent>();
        for(MessageContent MC: this.messageContents ) {
            if(MC.getToUsername().equals(username)) {
                messageContents.add(MC);
            }
        }
        return messageContents;
    }

}
