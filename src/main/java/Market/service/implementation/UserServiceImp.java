package Market.service.implementation;

import Market.model.userTypes.Users;
import Market.repo.UsersRepository;
import Market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UsersRepository usersRepository;

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
}
