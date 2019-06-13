package YapeCoupons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// TODO : Rename endpoints. Use meaningful, coherent names.
@Controller
public class Redirect {
    @RequestMapping("/")
    public String index() {
        return "login";
    }

    @RequestMapping("/home")
    public String login() {
        return "home";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/restorePassword")
    public String restorePassword() {
        return "restorePassword";
    }

    @RequestMapping("/restoreProcess")
    public String restoreProcess() {
        return "restoreProcess";
    }

    @RequestMapping("/restoreFinal")
    public String restoreFinal() {
        return "restoreFinal";
    }

    @RequestMapping("/register_local")
    public String registerLocal() {
        return "register_local";
    }

    @RequestMapping("/register_bank")
    public String registerBank() {
        return "register_bank";
    }

    @RequestMapping("/create_coupon")
    public String createCouponHTML() {
        return "create_coupon";
    }
}
