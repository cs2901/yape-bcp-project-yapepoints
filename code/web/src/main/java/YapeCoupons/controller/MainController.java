package YapeCoupons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/")
    public String Index() {
        return "index";
    }

    @RequestMapping("/login")
    public String Login() {
        return "login";
    }

    @RequestMapping("/register")
    public String Register() {
        return "register";
    }

    @RequestMapping("/register_local")
    public String RegisterLocal() {
        return "register_local";
    }

    // What is register_personal?
    @RequestMapping("/register_personal")
    public String RegisterPersonal() {
        return "register_personal";
    }

    @RequestMapping("/register_bank")
    public String RegisterBank() {
        return "register_bank";
    }

    @RequestMapping("/create_coupon")
    public String CreateCupon() {
        return "create_coupon";
    }


}
