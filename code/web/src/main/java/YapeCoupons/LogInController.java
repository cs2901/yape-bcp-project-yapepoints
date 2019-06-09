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

    @RequestMapping("/Register")
    public String Register() {
        return "register";
    }

    @RequestMapping("/Register_local")
    public String RegisterLocal() {
        return "register_local";
    }
    @RequestMapping("/Register_personal")
    public String RegisterPersonal() {
        return "register_personal";
    }

    @RequestMapping("/Register_bank")
    public String RegisterBank() {
        return "register_bank";
    }

    @RequestMapping("/Pag_inicio")
    public String PagInicio() {
        return "pag_inicio";
    }

    @RequestMapping("/Show_cupon")
    public String ShowCupon() {
        return "show_cupon";
    }

    @RequestMapping("/Create_cupon")
    public String CreateCupon() {
        return "create_coupon";
    }


}
