package YapeCoupons.controller;

import YapeCoupons.model.User;
import YapeCoupons.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Auth {
    @Autowired
    private UserRepository users;

    @RequestMapping("/")
    public String foo(Model model) {
        users.deleteAll();
        users.save(new User("Jose Leonidas", "Garcia Gonzales"));
        users.save(new User("Primer nombre", "Segundo nombre"));
        for (User user: users.findAll()) {
            System.out.println(user);
        }
        return "greeting";
    }
}
