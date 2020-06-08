package market.service;

import market.model.userTypes.Users;

import java.util.Set;

public interface UserService {
    void simpleSaveUserInRoleRepo(String username);
    Users getByUsername(String username);
    Set<Users> findByUserNames(String username);
    void removeUser(String username);
    void updateUser(String oldUsername,String newUsername, String plaintextPassword);
}
