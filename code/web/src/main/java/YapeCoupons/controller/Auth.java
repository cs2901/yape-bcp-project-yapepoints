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
            users.deleteAll();
            // En el primer formulario llamar a este método
            users.createUser("jose.garcia@utec.edu.pe", "Jose Leonidas", "Garcia Gonzales", "12345678", "1234");
            // En el segundo formulario llamar a este método
            users.setBankInformation("12345678", "Mi numero de cuenta");
            // En el tercer formulario llamar a este método
            users.setBusiness("12345678", "Negocio1", "Un negocio", "url", "ruc");
            // For debugging
            for (User user: users.findAll()) {
                System.out.println(user);
            }


            /*
            if (users.filledBankAccountInformation("12345678")) {
                System.out.println("Esta funcion será util para el middleware");
            }
            if (users.filledBusinessInformation("12345678")) {
                System.out.println("Lo mismo que lo anterior. La idea sería obtener el dni a partir de las sesion logeada actual");
            }
            System.out.println(users.filledBankAccountInformation("12345678"));
            */
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "greeting";
    }
}
