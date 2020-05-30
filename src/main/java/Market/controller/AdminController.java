package Market.controller;

import Market.repo.userTypeRepositories.UsersRepository;
import Market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("index")
    public String getAdminPage() {
        return "admin/index";
    }

    @RequestMapping(value = "searchUser", method = RequestMethod.GET)
    public String getUserSearch(Model model, @RequestParam(defaultValue = "") String username) {

        model.addAttribute("users", userService.findByUserNames(username));
        return "admin/searchUser";
    }
}
