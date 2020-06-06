package Market.service;

import Market.model.messages.Message;
import Market.model.userTypes.Admin;

public interface AdminService {

    Admin findByUsername(String username);
    boolean containsAdmin(String username);
    void save(Admin admin);
}
