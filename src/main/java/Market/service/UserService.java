package Market.service;

import Market.model.userTypes.Users;

import java.util.Set;

public interface UserService {
    public Set<Users> findByUserNames(String username);
    public void removeUser(String username);
}
