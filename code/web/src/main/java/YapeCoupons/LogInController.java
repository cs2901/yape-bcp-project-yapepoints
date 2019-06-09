package YapeCoupons;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class LogInController {
    @RequestMapping("/")
    public String index() {
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/register_local")
    public String register_local() {
        return "register_local";
    }
    @RequestMapping("/register_personal")
    public String register_personal() {
        return "register_personal";
    }

    @RequestMapping("/register_bank")
    public String register_bank() {
        return "register_bank";
    }

    @RequestMapping("/pag_inicio")
    public String pag_inicio() {
        return "pag_inicio";
    }

    @RequestMapping("/show_cupon")
    public String show_cupon() {
        return "show_cupon";
    }

    @RequestMapping("/create_coupon")
    public String create_coupon() {
        return "create_coupon";
    }


}
