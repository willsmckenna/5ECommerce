package Market.service;

import Market.model.userTypes.Users;
import Market.repo.userTypeRepositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    @Autowired
    UsersRepository usersRepository;

    public Set<Users>  findByUserNames(String username) {
        return usersRepository.findByUsernameLike("%" + username + "%");
    }

    public void removeUser(String username)
    {
        Users user = usersRepository.findByUsername(username);
        if(user != null){
            usersRepository.delete(user);
        }
    }
}
