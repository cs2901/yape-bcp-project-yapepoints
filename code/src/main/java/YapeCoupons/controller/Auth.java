package YapeCoupons.controller;

import YapeCoupons.model.User;
import YapeCoupons.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Auth {
    @Autowired
    private UserService users;

    @RequestMapping("/")
    public String foo(Model model) {
        try {
            users.createUser("Jose Leonidas", "Garcia Gonzales", "12345678", "1234");
            //users.createUser("Primer nombre", "Segundo nombre", "12345678", "1234");
            //for (User user: users.findAll()) {
            //    System.out.println(user);
            //}
            System.out.println(users.findByDni("12345678"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "greeting";
    }
}
