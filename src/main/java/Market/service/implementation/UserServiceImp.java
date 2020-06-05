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
    public void updatedPassword(String username, String plaintextPassword) {
        Users user = this.usersRepository.findByUsername(username);
        user.setPassword(passwordEncoder.encode(plaintextPassword));
        this.usersRepository.save(user);
    }
}
