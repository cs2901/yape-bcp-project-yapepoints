package YapeCoupons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

// TODO : Rename endpoints. Use meaningful, coherent names.
@Controller
public class Redirect {
    private static final String title = "YapePoints";
    @RequestMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("title", title);
        return "index";
    }

    @RequestMapping("/home")
    public String home(final HttpServletRequest req, ModelMap map) {
        String name = req.getSession().getAttribute("name").toString();
        map.addAttribute("name", name);
        map.addAttribute("title", title + " - Home");
        return "home";
    }

    @RequestMapping("/restorePassword")
    public String restorePassword() { return "restorePassword"; }

    @RequestMapping("/changePassword")
    public String changePassword(ModelMap map) {
        map.addAttribute("title", title + " - Cambiar constraseña");
        return "changePassword";
    }

    @RequestMapping("/create_coupon")
    public String createCouponHTML(ModelMap map) {
        map.addAttribute("title", title + " - Crear cupón");
        return "create_coupon";
    }

}
