package YapeCoupons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

// TODO : Rename endpoints. Use meaningful, coherent names.
@Controller
public class Redirect {
    @RequestMapping("/")
    public String index() { return "login"; }

    @RequestMapping("/home")
    public String home(final HttpServletRequest req, ModelMap map) {
        String name = req.getSession().getAttribute("name").toString();
        map.addAttribute("name", name);
        return "home";
    }

    @RequestMapping("/restorePassword")
    public String restorePassword() { return "restorePassword"; }

    @RequestMapping("/changePassword")
    public String changePassword() { return "changePassword"; }

    @RequestMapping("/create_coupon")
    public String createCouponHTML() { return "create_coupon"; }

}
