package Market.Controller;

import Market.Model.Users;
import Market.Repo.UsersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {


        private final UsersRepository usersRepository;

        public LoginController(UsersRepository usersRepository) {
            this.usersRepository = usersRepository;
        }

    @GetMapping({"/login"})
    public String login() {
        return "logIn/login";
    }

    //@PostMapping({"/login"})
    //public String login(Model model) {
    //    return "productview/browse";
    //}


}
