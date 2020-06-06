package Market.service;

import Market.model.messages.Message;
import Market.model.userTypes.Admin;

import java.util.List;
import java.util.Map;

public interface AdminService {

    Admin findByUsername(String username);
    boolean containsAdmin(String username);
    void save(Admin admin);
    List<Message> getAdminMessages(String username);
}
