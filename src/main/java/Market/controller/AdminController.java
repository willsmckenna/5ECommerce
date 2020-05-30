package Market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {

    @GetMapping("index")
    public String getAdminPage() {
        return "admin/index";
    }

    @GetMapping("searchUser")
    public String getUserSearch() {
        return "admin/searchUser";
    }
}
