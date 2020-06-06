package Market.service;

import Market.model.messages.Message;

import java.util.Set;

public interface MessagingService {
    void saveMessage(Message message, String from, String to);
    Set<Message> getAllMessages(String username);
}
