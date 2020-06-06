package Market.service;

import Market.model.messages.Message;

import java.util.Map;
public interface MessagingService {
    void saveMessage(Message message, String from, String to);
    Map<Integer, Message> getAllMessages(String username);
}
