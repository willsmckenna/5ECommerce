package Market.service;

import Market.model.userTypes.Users;

import java.util.Set;

public interface UserService {
   public  Users getByUsername(String username);
    Set<Users> findByUserNames(String username);
    void removeUser(String username);
    void updateUser(String oldUsername,String newUsername, String plaintextPassword);
}
