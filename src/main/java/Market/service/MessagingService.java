package Market.service;

import Market.model.messages.Message;

import java.util.List;
import java.util.Map;
public interface MessagingService {
    void saveMessage(Message message, String from, String to);
    List<Message>  getUsersMessages(String username);
    List<Message>  getByUserWhereFromIsLike(String username, String fromLike);

}
