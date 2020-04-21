package Market.Controller;


import Market.Model.alien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class alienController {
    @Autowired
    Market.Repo.alienRepo alienRepo;

    /**
     * return simple insert page and search page
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "alienlogin";
    }

    /**
     * insert alien object into database table
     * @param alien
     * @return
     */
    @RequestMapping("/addAlien")
    public String add_alien(alien alien){
        alienRepo.save(alien);
        return "alienlogin";
    }

    /**
     * when user click search, we will look it up and return a different jsp
     * @param id
     * @return
     */
    @RequestMapping("/searchAlien")
    public ModelAndView getAlien(@RequestParam int id){
        //return a new jsp back to user
        ModelAndView m = new ModelAndView("showAlien");
        alien a = alienRepo.findById(id).orElse(new alien());
        m.addObject(a);
        return m;
    }

}
