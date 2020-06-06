package Market.repo;

import Market.model.messages.MessageContent;
import Market.model.messages.MessageHeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageHeader, Long> {
}
