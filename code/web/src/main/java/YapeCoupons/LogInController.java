package YapeCoupons;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class LogInController {
    @RequestMapping("/")
    public String Index() {
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

    @RequestMapping("/register_personal")
    public String RegisterPersonal() {
        return "register_personal";
    }

    @RequestMapping("/register_bank")
    public String RegisterBank() {
        return "register_bank";
    }

    @RequestMapping("/pag_inicio")
    public String PagInicio() {
        return "pag_inicio";
    }

    @RequestMapping("/show_cupon")
    public String ShowCupon() {
        return "show_cupon";
    }

    @RequestMapping("/create_cupon")
    public String CreateCupon() {
        return "create_coupon";
    }


}
