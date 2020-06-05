package Market.service.implementation;

import Market.model.userTypes.Users;
import Market.repo.UsersRepository;
import Market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Users getByUsername(String username) {
        return this.usersRepository.findByUsername(username);
    }

    public Set<Users>  findByUserNames(String username) {
        return usersRepository.findByUsernameLike("%" + username + "%");
    }

    public void removeUser(String username) {
        Users user = usersRepository.findByUsername(username);
        if(user != null){
            usersRepository.delete(user);
        }
    }

    @Override
    public void updateUser(String oldUsername,String newUsername, String plaintextPassword) {
        Users user = this.usersRepository.findByUsername(oldUsername);
        user.setUsername(newUsername);
        user.setPassword(passwordEncoder.encode(plaintextPassword));
        this.usersRepository.save(user);
    }
}
