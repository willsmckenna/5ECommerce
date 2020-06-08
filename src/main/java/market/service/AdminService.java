package market.service;

import market.model.messages.Message;
import market.model.userTypes.Admin;

import java.util.List;

public interface AdminService {

    Admin findByUsername(String username);
    boolean containsAdmin(String username);
    void save(Admin admin);
    List<Message> getAdminMessages(String username);
}
