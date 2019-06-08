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

    @RequestMapping("/example")
    public String example(Model model) {
        try {
            // En el primer formulario llamar a este método
            users.createUser("Jose Leonidas", "Garcia Gonzales", "12345678", "1234");
            // En el segundo formulario llamar a este método
            users.setBankInformation("12345678", "Mi numero de cuenta");
            // En el tercer formulario llamar a este método
            users.setBusiness("12345678", "Negocio1", "Un negocio", "url");
            // For debugging
            for (User user: users.findAll()) {
                System.out.println(user);
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "greeting";
    }
}
