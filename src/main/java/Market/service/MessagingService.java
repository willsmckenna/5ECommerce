package Market.service;

import Market.model.messages.Message;

public interface MessagingService {
    void saveMessage(Message message, String from, String to);
}
