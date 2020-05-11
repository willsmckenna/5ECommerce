package Market.BootStrap;

import Market.Model.Buyer;
import Market.Model.Users;
import Market.Repo.UserTypeRepository;
import Market.Repo.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/*
    Keep this until we can fully figure out flyway, populates tables
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final UsersRepository usersRepository;
    private final UserTypeRepository userTypeRepository;

    public BootStrapData(UsersRepository usersRepository, UserTypeRepository userTypeRepository)
    {
        this.usersRepository = usersRepository;
        this.userTypeRepository = userTypeRepository;
    }


    @Override
    public void run(String... args) throws Exception
    {
        System.out.println("Started in Bootstrap");
        Users user_1 = new Users();
        user_1.setName("Its a Mario");
        usersRepository.save(user_1);

        System.out.println("Number of USERS: " + usersRepository.count());

    }
}
